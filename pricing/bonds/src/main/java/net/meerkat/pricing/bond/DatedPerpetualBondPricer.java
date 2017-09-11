package net.meerkat.pricing.bond;

import java.time.LocalDate;
import java.util.function.Function;

import javax.annotation.Nonnull;

import net.coljate.list.List;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.bond.PerpetualBond;
import net.meerkat.instrument.bond.coupon.FixedCoupon;
import net.meerkat.instrument.cash.CashPayment;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRates;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.interpolation.InterestRateInterpolator;
import net.meerkat.pricing.shifts.ExchangeRateShifts.ExchangeRateShifter;
import net.meerkat.pricing.shifts.InterestRateShifts.InterestRateShifter;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.suppliers.lazy.Lazy;

/**
 *
 * @author Ollie
 */
public class DatedPerpetualBondPricer implements BondPricer<PerpetualBond<?>> {

    private final LocalDate valuationDate;
    private final ExchangeRates exchangeRates;
    private final Function<? super CurrencyId, ? extends InterestRate> discountRates;
    private final InterestRateInterpolator interestRateInterpolator;

    public DatedPerpetualBondPricer(
            final LocalDate valuationDate,
            final ExchangeRates exchangeRates,
            final Function<? super CurrencyId, ? extends InterestRate> discountRates,
            final InterestRateInterpolator interestRateInterpolator) {
        this.valuationDate = valuationDate;
        this.exchangeRates = exchangeRates;
        this.discountRates = discountRates;
        this.interestRateInterpolator = interestRateInterpolator;
    }

    @Override
    public <C extends CurrencyId> BondPrice.Shiftable<C> price(
            final PerpetualBond<?> bond,
            final C currency) {
        final InterestRate discountRate = discountRates.apply(currency);
        return new PerpetualBondPrice<>(bond, currency, valuationDate, exchangeRates, discountRate, interestRateInterpolator, BondShifts.none());
    }

    private static final class PerpetualBondPrice<F extends CurrencyId, C extends CurrencyId>
            implements BondPrice.Shiftable<C>, ExchangeRateShifter, InterestRateShifter {

        private final PerpetualBond<F> bond;
        private final C currency;
        private final LocalDate date;
        private final ExchangeRates fxRates;
        private final InterestRate discountRate;
        private final InterestRateInterpolator interestRateInterpolator;
        private final BondShifts shifts;

        PerpetualBondPrice(
                final PerpetualBond<F> bond,
                final C currency,
                final LocalDate date,
                final ExchangeRates fxRates,
                final InterestRate discountRate,
                final InterestRateInterpolator interestRateInterpolator,
                final BondShifts shifts) {
            this.currency = currency;
            this.shifts = shifts;
            this.date = date;
            this.fxRates = fxRates;
            this.discountRate = discountRate;
            this.bond = bond;
            this.interestRateInterpolator = interestRateInterpolator;
        }

        @Override
        public Money<C> par() {
            return this.shift(bond.par(), shifts, currency, fxRates);
        }

        Money<C> shiftedCoupon() {
            return this.shift(bond.coupons().yearlyAmount(), shifts, currency, fxRates);
        }

        Percentage shiftedAnnualRate() {
            return this.shift(bond.coupons().yearlyRate(), shifts).annualRate();
        }

        private final Lazy<Money<C>> cleanValue = Lazy.loadOnceNonNull(this::calculateCleanValue);

        @Override
        public Money<C> clean() {
            return cleanValue.get();
        }

        @Nonnull
        private Money<C> calculateCleanValue() {
            return this.shiftedCoupon().over(this.shiftedAnnualRate());
        }

        private InterestRate shiftedDiscountRate() {
            return this.shift(discountRate, shifts);
        }

        @Override
        public List<CashPayment<C>> cleanFlow(final LocalDate start, final LocalDate end) {
            final InterestRate discountRate = PerpetualBondPrice.this.shiftedDiscountRate();
            final List<FixedCoupon<?>> coupons = bond.coupons().between(start, end);
            return coupons.transform(coupon -> {
                final Money<C> couponAmount = this.shift(coupon.paymentAmount(), shifts, currency, fxRates);
                final Money<C> discountedAmount = discountRate.discount(couponAmount, date, coupon.paymentDate(), interestRateInterpolator);
                return CashPayment.of(coupon.paymentDate(), discountedAmount);
            });
        }

        private final Lazy<Money<C>> accruedInterest = Lazy.loadOnceNonNull(this::calculateAccuredInterest);

        @Override
        public Money<C> accruedInterest() {
            return accruedInterest.get();
        }

        @Override
        public Money<C> dirty() {
            return this.clean().plus(this.accruedInterest());
        }

        @Nonnull
        private Money<C> calculateAccuredInterest() {
            final FixedCoupon<?> priorCoupon = bond.coupons().prior(date);
            final Money<C> priorAmount = this.shiftedCoupon();
            return this.shiftedDiscountRate().accrue(priorAmount, priorCoupon.paymentDate(), date, interestRateInterpolator).minus(priorAmount);
        }

        @Override
        public BondPrice.Shiftable<C> shift(final BondShifts shifts) {
            return shifts == this.shifts
                    ? this
                    : new PerpetualBondPrice<>(bond, currency, date, fxRates, discountRate, interestRateInterpolator, shifts);
        }

        @Override
        public C currencyId() {
            return currency;
        }

        @Override
        public Percentage yieldToMaturity() {
            return bond.coupons().yearlyRate().annualRate();
        }

        @Override
        public ExplanationBuilder explain() {
            return BondPrice.Shiftable.super.explain()
                    .put("bond", bond)
                    .put("date", date)
                    .put("accrued interest", this.accruedInterest())
                    .put("discount rate", discountRate)
                    .put("shifts", shifts)
                    .put("shifted discount rate", this.shiftedDiscountRate())
                    .put("shifted annual rate", this.shiftedAnnualRate())
                    .put("shifted coupon", this.shiftedCoupon());
        }

    }

}
