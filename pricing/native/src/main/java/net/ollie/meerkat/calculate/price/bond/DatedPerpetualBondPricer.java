package net.ollie.meerkat.calculate.price.bond;

import java.time.LocalDate;
import java.util.List;
import java.util.function.BiFunction;

import javax.annotation.Nonnull;

import net.meerkat.money.Money;
import net.meerkat.money.currency.Currency;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.security.bond.PerpetualBond;
import net.meerkat.security.bond.coupon.FixedRateCoupon;
import net.meerkat.security.fx.CashPayment;
import net.ollie.goat.collection.list.Lists;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.suppliers.lazy.Lazy;
import net.ollie.meerkat.calculate.fx.ExchangeRates;
import net.ollie.meerkat.calculate.fx.ExchangeRatesProvider;
import net.ollie.meerkat.calculate.price.shifts.ExchangeRateShifts.ExchangeRateShifter;
import net.ollie.meerkat.calculate.price.shifts.InterestRateShifts.InterestRateShifter;

/**
 *
 * @author Ollie
 */
public class DatedPerpetualBondPricer implements BondTypePricer<LocalDate, PerpetualBond> {

    private final ExchangeRatesProvider<LocalDate> exchangeRatesProvider;
    private final BiFunction<? super LocalDate, ? super Currency, ? extends InterestRate> getDiscountRates;

    public DatedPerpetualBondPricer(
            final ExchangeRatesProvider<LocalDate> getExchangeRates,
            final BiFunction<? super LocalDate, ? super Currency, ? extends InterestRate> getDiscountRates) {
        this.exchangeRatesProvider = getExchangeRates;
        this.getDiscountRates = getDiscountRates;
    }

    @Override
    public <C extends Currency> BondPrice.Shiftable<C> price(
            final LocalDate date,
            final PerpetualBond bond,
            final C currency) {
        final ExchangeRates exchangeRates = exchangeRatesProvider.require(date);
        final InterestRate discountRate = getDiscountRates.apply(date, currency);
        return new PerpetualBondPrice<>(bond, currency, date, exchangeRates, discountRate, BondShifts.none());
    }

    private static final class PerpetualBondPrice<C extends Currency>
            implements BondPrice.Shiftable<C>, ExchangeRateShifter, InterestRateShifter {

        private final PerpetualBond bond;
        private final C currency;
        private final LocalDate date;
        private final ExchangeRates fxRates;
        private final InterestRate discountRate;
        private final BondShifts shifts;

        PerpetualBondPrice(
                final PerpetualBond bond,
                final C currency,
                final LocalDate date,
                final ExchangeRates fxRates,
                final InterestRate discountRate,
                final BondShifts shifts) {
            this.currency = currency;
            this.shifts = shifts;
            this.date = date;
            this.fxRates = fxRates;
            this.discountRate = discountRate;
            this.bond = bond;
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
            final List<FixedRateCoupon<?>> coupons = bond.coupons().between(start, end);
            return Lists.lazilyComputed(coupons.size(), index -> {
                final FixedRateCoupon<?> coupon = coupons.get(index);
                final Money<C> couponAmount = PerpetualBondPrice.this.shift(coupon.amount(), shifts, currency, fxRates);
                final Money<C> discountedAmount = discountRate.discount(couponAmount, date, coupon.date());
                return CashPayment.of(coupon.date(), discountedAmount);
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
            final FixedRateCoupon<?> priorCoupon = bond.coupons().prior(date);
            final Money<C> priorAmount = this.shiftedCoupon();
            return this.shiftedDiscountRate().accrue(priorAmount, priorCoupon.date(), date).minus(priorAmount);
        }

        @Override
        public BondPrice.Shiftable<C> shift(final BondShifts shifts) {
            return shifts == this.shifts
                    ? this
                    : new PerpetualBondPrice<>(bond, currency, date, fxRates, discountRate, shifts);
        }

        @Override
        public C currency() {
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
