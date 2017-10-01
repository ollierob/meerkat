package net.meerkat.pricing.bond;

import java.time.LocalDate;
import java.time.Period;

import net.meerkat.Explained;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.bond.StraightBond;
import net.meerkat.instrument.bond.coupon.BondCoupon;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRates;
import net.meerkat.money.fx.ExchangeRatesProvider;
import net.meerkat.money.fx.exception.ExchangeRateException;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.InterestRates;
import net.meerkat.money.interest.InterestRatesProvider;
import net.meerkat.money.interest.exception.InterestRateException;
import net.meerkat.money.interest.interpolation.InterestRateInterpolator;
import net.meerkat.pricing.bond.shifts.BondShifts;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.suppliers.lazy.Lazy;

/**
 * Computes the value of a {@link StraightBond} by summing the present value of all future coupons.
 *
 * @author ollie
 * @see InterestRates#discountRate
 */
public class DailyStraightBondPresentValuePricer implements BondPricer<LocalDate, StraightBond> {

    private final InterestRateInterpolator interestRateInterpolator;
    private final InterestRatesProvider<LocalDate> interestRatesProvider;
    private final ExchangeRatesProvider<LocalDate> exchangeRatesProvider;

    public DailyStraightBondPresentValuePricer(
            final InterestRateInterpolator interestRateInterpolator,
            final InterestRatesProvider<LocalDate> interestRatesProvider,
            final ExchangeRatesProvider<LocalDate> exchangeRatesProvider) {
        this.interestRateInterpolator = interestRateInterpolator;
        this.interestRatesProvider = interestRatesProvider;
        this.exchangeRatesProvider = exchangeRatesProvider;
    }

    @Override
    public <C extends CurrencyId> BondPrice.Shiftable<C> price(
            final LocalDate valueDate,
            final StraightBond bond,
            final C currency,
            final BondShifts shifts)
            throws BondPriceException {
        try {
            final InterestRates interestRates = interestRatesProvider.require(valueDate);
            final ExchangeRates exchangeRates = exchangeRatesProvider.require(valueDate);
            return new StraightBondPrice<>(valueDate, bond, currency, interestRates, exchangeRates, shifts);
        } catch (final InterestRateException | ExchangeRateException ex) {
            throw new BondPriceException(ex);
        }
    }

    protected <C extends CurrencyId> Explained<Money<C>> explainCleanValue(final StraightBondValuationContext<C> context) {
        final Money<C> parPv = this.presentParValue(context);
        final Money<C> couponPv = this.presentCouponsValue(context);
        final Money<C> total = couponPv.plus(parPv);
        return new Explained<>(total, ex -> ex.put("par PV", parPv).put("coupon PV", couponPv));
    }

    protected <C extends CurrencyId> Money<C> presentParValue(final StraightBondValuationContext<C> context) {
        final LocalDate parDate = context.bond().dates().maturityDate();
        final Money<?> parPv = context.discount(context.par(), parDate);
        return context.convert(parPv);
    }

    protected <C extends CurrencyId> Money<C> presentCouponsValue(final StraightBondValuationContext<C> context) {
        return context.unpaidCoupons()
                .transform(coupon -> this.presentCouponValue(context, coupon))
                .reduce(Money::nullSafePlus);
    }

    protected <C extends CurrencyId> Money<C> presentCouponValue(
            final StraightBondValuationContext<C> context,
            final BondCoupon coupon) {
        final Money<?> couponValue = coupon.couponValue().orElseGet(() -> this.forwardCouponValue(context, coupon));
        return context.discount(couponValue, coupon.paymentDate());
    }

    protected <C extends CurrencyId> Money<C> forwardCouponValue(
            final StraightBondValuationContext<C> context,
            final BondCoupon coupon) {
        final Money<?> par = context.par();
        final Period couponFrequency = context.bond().coupons().frequency();
        final LocalDate couponDate = coupon.paymentDate();
        final InterestRate accrualRate = context.interestRate(coupon);
        final Money<?> accrued = accrualRate.accrued(par, couponDate.minus(couponFrequency), couponDate, context.interestRateInterpolator());
        return context.convert(accrued);
    }

    protected <C extends CurrencyId> Explained<Money<C>> explainAccruedInterest(final StraightBondValuationContext<C> context) {
        final BondCoupon currentCoupon = context.currentCoupon();
        if (currentCoupon == null) {
            return new Explained<>(Money.zero(context.currencyId()), ex -> ex.put("current coupon", null));
        }
        final InterestRate couponRate = context.interestRate(currentCoupon);
        final Money<?> accrued = couponRate.accrue(context.par(), context.valueDate(), currentCoupon.paymentDate(), context.interestRateInterpolator());
        return new Explained<>(
                context.convert(accrued),
                ex -> ex.put("current coupon", currentCoupon).put("coupon rate", couponRate));
    }

    private final class StraightBondPrice<C extends CurrencyId>
            implements BondPrice.Shiftable<C>, StraightBondValuationContext<C> {

        private final LocalDate valueDate;
        private final StraightBond bond;
        private final C currencyId;
        private final BondShifts shifts;
        private final InterestRates interestRates;
        private final ExchangeRates fxRates;

        StraightBondPrice(
                final LocalDate valueDate,
                final StraightBond bond,
                final C currencyId,
                final InterestRates interestRates,
                final ExchangeRates fxRates,
                final BondShifts shifts) {
            this.valueDate = valueDate;
            this.bond = bond;
            this.currencyId = currencyId;
            this.shifts = shifts;
            this.interestRates = interestRates;
            this.fxRates = fxRates;
        }

        @Override
        public C currencyId() {
            return currencyId;
        }

        @Override
        public LocalDate valueDate() {
            return valueDate;
        }

        @Override
        public StraightBond bond() {
            return bond;
        }

        @Override
        public InterestRate discountRate(final CurrencyId currencyId) {
            return shifts.shift(interestRates.discountRate(currencyId));
        }

        @Override
        public InterestRateInterpolator interestRateInterpolator() {
            return interestRateInterpolator;
        }

        @Override
        public <F extends CurrencyId> Money<C> convert(final Money<F> money) {
            return shifts.shift(fxRates.rate(money.currencyId(), currencyId)).convert(money);
        }

        @Override
        public InterestRate interestRate(final BondCoupon coupon) {
            return shifts.shift(coupon.rate(interestRates));
        }

        @Override
        public Money<C> par() {
            return this.convert(bond.par());
        }

        private final Lazy<Explained<Money<C>>> clean = Lazy.loadOnce(() -> explainCleanValue(this));

        @Override
        public Money<C> clean() {
            return clean.get().value();
        }

        private final Lazy<Explained<Money<C>>> accrued = Lazy.loadOnce(() -> explainAccruedInterest(this));

        @Override
        public Money<C> accruedInterest() {
            return accrued.get().value();
        }

        @Override
        public Money<C> dirty() {
            return this.clean().plus(this.accruedInterest());
        }

        @Override
        public Percentage yieldToMaturity() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Shiftable<C> shift(final BondShifts shifts) {
            return new StraightBondPrice<>(valueDate, bond, currencyId, interestRates, fxRates, shifts);
        }

        @Override
        public ExplanationBuilder explain() {
            return Shiftable.super.explain()
                    .put("clean", clean.get())
                    .put("accrued", accrued.get());
        }

    }

}
