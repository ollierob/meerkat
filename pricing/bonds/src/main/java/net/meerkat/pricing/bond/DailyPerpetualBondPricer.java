package net.meerkat.pricing.bond;

import java.time.LocalDate;
import java.util.function.BiFunction;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.bond.PerpetualBond;
import net.meerkat.instrument.bond.coupon.FixedCoupon;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRates;
import net.meerkat.money.fx.ExchangeRatesProvider;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.interpolation.InterestRateInterpolator;
import net.meerkat.pricing.bond.shifts.BondShifts;
import net.meerkat.pricing.shifts.interest.InterestRateShifts.InterestRateShifter;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.suppliers.lazy.Lazy;

/**
 *
 * @author Ollie
 */
public class DailyPerpetualBondPricer implements BondPricer<LocalDate, PerpetualBond<?>> {

    private final ExchangeRatesProvider<LocalDate> exchangeRatesProvider;
    private final BiFunction<? super LocalDate, ? super CurrencyId, ? extends InterestRate> getDiscountRates;
    private final InterestRateInterpolator interestRateInterpolator;

    public DailyPerpetualBondPricer(
            final ExchangeRatesProvider<LocalDate> getExchangeRates,
            final BiFunction<? super LocalDate, ? super CurrencyId, ? extends InterestRate> getDiscountRates,
            final InterestRateInterpolator interestRateInterpolator) {
        this.exchangeRatesProvider = getExchangeRates;
        this.getDiscountRates = getDiscountRates;
        this.interestRateInterpolator = interestRateInterpolator;
    }

    @Override
    public <C extends CurrencyId> BondPrice.Shiftable<C> price(
            final LocalDate date,
            final PerpetualBond<?> bond,
            final C currency,
            final BondShifts bondShifts) {
        final ExchangeRates exchangeRates = exchangeRatesProvider.require(date);
        final InterestRate discountRate = getDiscountRates.apply(date, currency);
        return new PerpetualBondPrice<>(bond, currency, date, exchangeRates, discountRate, interestRateInterpolator, bondShifts);
    }

    private static final class PerpetualBondPrice<F extends CurrencyId, C extends CurrencyId>
            implements BondPrice.Shiftable<C>, InterestRateShifter {

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

        private ExchangeRates fxRates() {
            return shifts.shift(fxRates);
        }

        private Money<C> shift(final Money<?> money) {
            return this.fxRates().convert(money, currency);
        }

        @Override
        public Money<C> par() {
            return this.shift(bond.par());
        }

        Money<C> shiftedCoupon() {
            return this.shift(bond.coupons().yearlyAmount());
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
