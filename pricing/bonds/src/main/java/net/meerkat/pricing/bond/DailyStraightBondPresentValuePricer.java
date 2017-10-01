package net.meerkat.pricing.bond;

import java.time.LocalDate;
import java.time.Period;

import javax.annotation.Nonnull;

import net.coljate.list.List;
import net.meerkat.Explained;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.bond.StraightBond;
import net.meerkat.instrument.bond.coupon.BondCoupon;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRates;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.InterestRates;
import net.meerkat.money.interest.InterestRatesProvider;
import net.meerkat.money.interest.interpolation.InterestRateInterpolator;
import net.meerkat.pricing.bond.shifts.BondShifts;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.suppliers.lazy.Lazy;

/**
 *
 * @author ollie
 */
public class DailyStraightBondPresentValuePricer implements BondPricer<LocalDate, StraightBond> {

    private final InterestRateInterpolator interestRateInterpolator;
    private final InterestRatesProvider<LocalDate> interestRatesProvider;

    public DailyStraightBondPresentValuePricer(
            final InterestRateInterpolator interestRateInterpolator,
            final InterestRatesProvider<LocalDate> interestRatesProvider) {
        this.interestRateInterpolator = interestRateInterpolator;
        this.interestRatesProvider = interestRatesProvider;
    }

    @Override
    public <C extends CurrencyId> BondPrice.Shiftable<C> price(
            final LocalDate date,
            final StraightBond bond,
            final C currency,
            final BondShifts shifts)
            throws BondPriceException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    protected InterestRate discountRate(final LocalDate valueDate, final CurrencyId currency) {
        throw new UnsupportedOperationException();
    }

    protected <C extends CurrencyId> Explained<Money<C>> cleanValue(final StraightBondValuationContext<C> context) {
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

    protected interface StraightBondValuationContext<C extends CurrencyId> {

        C currencyId();

        LocalDate valueDate();

        StraightBond bond();

        default Money<?> par() {
            return this.bond().par();
        }

        default List<? extends BondCoupon> unpaidCoupons() {
            return this.bond().coupons().onOrAfter(this.valueDate());
        }

        @Nonnull
        InterestRate discountRate();

        default Money<C> discount(final Money<?> money, final LocalDate paymentDate) {
            final Money<?> moneyPv = this.discountRate().discount(money, this.valueDate(), paymentDate, this.interestRateInterpolator());
            return this.convert(moneyPv);
        }

        InterestRateInterpolator interestRateInterpolator();

        <F extends CurrencyId> Money<C> convert(Money<F> money);

        InterestRate interestRate(BondCoupon coupon);

    }

    private final class PresentValuePrice<C extends CurrencyId>
            implements BondPrice.Shiftable<C>, StraightBondValuationContext<C> {

        private final LocalDate valueDate;
        private final StraightBond bond;
        private final C currencyId;
        private final BondShifts shifts;
        private final InterestRate discountRate;
        private final InterestRates interestRates;
        private final ExchangeRates fxRates;

        public PresentValuePrice(
                final LocalDate valueDate,
                final StraightBond bond,
                final C currencyId,
                final BondShifts shifts,
                final InterestRate discountRate,
                final InterestRates interestRates,
                final ExchangeRates fxRates) {
            this.valueDate = valueDate;
            this.bond = bond;
            this.currencyId = currencyId;
            this.shifts = shifts;
            this.discountRate = discountRate;
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
        public InterestRate discountRate() {
            return shifts.shift(discountRate);
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

        private final Lazy<Explained<Money<C>>> clean = Lazy.loadOnce(this::computeClean);

        private Explained<Money<C>> computeClean() {
            return DailyStraightBondPresentValuePricer.this.cleanValue(this);
        }

        @Override
        public Money<C> clean() {
            return clean.get().value();
        }

        @Override
        public Money<C> dirty() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Percentage yieldToMaturity() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Shiftable<C> shift(final BondShifts shifts) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public ExplanationBuilder explain() {
            return Shiftable.super.explain()
                    .put("clean", clean.get());
        }

    }

}
