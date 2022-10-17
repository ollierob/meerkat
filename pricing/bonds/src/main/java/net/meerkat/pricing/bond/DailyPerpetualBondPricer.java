package net.meerkat.pricing.bond;

import net.meerkat.functions.suppliers.lazy.Lazy;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.bond.PerpetualBond;
import net.meerkat.instrument.bond.coupon.FixedCoupon;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRateProvider;
import net.meerkat.money.fx.ExchangeRateSnapshot;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.interpolation.InterestRateInterpolator;
import net.meerkat.numeric.percentage.Percentage;
import net.meerkat.pricing.bond.shifts.BondPriceShifts;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.util.function.BiFunction;

/**
 *
 * @author Ollie
 */
public class DailyPerpetualBondPricer implements BondPricer<LocalDate, PerpetualBond<?>> {

    private final ExchangeRateProvider<LocalDate> exchangeRatesProvider;
    private final BiFunction<? super LocalDate, ? super CurrencyId, ? extends InterestRate> getDiscountRates;
    private final InterestRateInterpolator interestRateInterpolator;

    public DailyPerpetualBondPricer(
            final ExchangeRateProvider<LocalDate> getExchangeRates,
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
            final BondPriceShifts bondPriceShifts) {
        final var exchangeRates = exchangeRatesProvider.require(date);
        final var discountRate = getDiscountRates.apply(date, currency);
        return new PerpetualBondPrice<>(bond, currency, date, exchangeRates, discountRate, interestRateInterpolator, bondPriceShifts);
    }

    private static final class PerpetualBondPrice<F extends CurrencyId, C extends CurrencyId>
            implements BondPrice.Shiftable<C> {

        private final PerpetualBond<F> bond;
        private final C currency;
        private final LocalDate date;
        private final ExchangeRateSnapshot fxRates;
        private final InterestRate discountRate;
        private final InterestRateInterpolator interestRateInterpolator;
        private final BondPriceShifts shifts;

        PerpetualBondPrice(
                final PerpetualBond<F> bond,
                final C currency,
                final LocalDate date,
                final ExchangeRateSnapshot fxRates,
                final InterestRate discountRate,
                final InterestRateInterpolator interestRateInterpolator,
                final BondPriceShifts shifts) {
            this.currency = currency;
            this.shifts = shifts;
            this.date = date;
            this.fxRates = fxRates;
            this.discountRate = discountRate;
            this.bond = bond;
            this.interestRateInterpolator = interestRateInterpolator;
        }

        private ExchangeRateSnapshot fxRates() {
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
            return shifts.shift(bond.coupons().yearlyRate()).annualRate();
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
            return shifts.shift(discountRate);
        }

        private final Lazy<Money<C>> accruedInterest = Lazy.loadOnceNonNull(this::calculateAccruedInterest);

        @Override
        public Money<C> accruedInterest() {
            return accruedInterest.get();
        }

        @Override
        public Money<C> dirty() {
            return this.clean().plus(this.accruedInterest());
        }

        @Nonnull
        private Money<C> calculateAccruedInterest() {
            final FixedCoupon<?> priorCoupon = bond.coupons().latestBefore(date);
            final Money<C> priorAmount = this.shiftedCoupon();
            return this.shiftedDiscountRate().accrue(priorAmount, priorCoupon.paymentDate(), date, interestRateInterpolator).minus(priorAmount);
        }

        @Override
        public BondPrice.Shiftable<C> shift(final BondPriceShifts shifts) {
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
