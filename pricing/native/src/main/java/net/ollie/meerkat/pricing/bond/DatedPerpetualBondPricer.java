package net.ollie.meerkat.pricing.bond;

import java.time.LocalDate;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.annotation.Nonnull;

import net.ollie.goat.suppliers.lazy.Lazy;
import net.ollie.meerkat.calculate.fx.ExchangeRateCalculator;
import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.calculate.price.bond.BondShifts;
import net.ollie.meerkat.calculate.price.bond.BondTypePricer;
import net.ollie.meerkat.calculate.price.shifts.ExchangeRateShifts.ExchangeRateShifter;
import net.ollie.meerkat.calculate.price.shifts.InterestRateShifts.InterestRateShifter;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.interest.InterestRate;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.bond.PerpetualBond;
import net.ollie.meerkat.security.bond.coupon.FixedCoupon;
import net.ollie.meerkat.security.fx.CashPayment;
import net.ollie.meerkat.time.interim.Interval;
import net.ollie.meerkat.utils.collections.Lists;

/**
 *
 * @author Ollie
 */
public class DatedPerpetualBondPricer implements BondTypePricer<LocalDate, PerpetualBond> {

    private final Function<? super LocalDate, ? extends ExchangeRateCalculator> getExchangeRates;
    private final BiFunction<? super LocalDate, ? super CurrencyId, ? extends InterestRate> getDiscountRates;

    public DatedPerpetualBondPricer(
            final Function<? super LocalDate, ? extends ExchangeRateCalculator> getExchangeRates,
            final BiFunction<? super LocalDate, ? super CurrencyId, ? extends InterestRate> getDiscountRates) {
        this.getExchangeRates = getExchangeRates;
        this.getDiscountRates = getDiscountRates;
    }

    @Override
    public <C extends CurrencyId> BondPrice<C> price(
            final LocalDate date,
            final PerpetualBond bond,
            final C currency) {
        final ExchangeRateCalculator exchangeRates = getExchangeRates.apply(date);
        final InterestRate discountRate = getDiscountRates.apply(date, currency);
        return new PerpetualBondPrice<>(bond, currency, date, exchangeRates, discountRate, BondShifts.none());
    }

    private static final class PerpetualBondPrice<C extends CurrencyId>
            implements BondPrice<C>, ExchangeRateShifter, InterestRateShifter {

        private final PerpetualBond bond;
        private final C currency;
        private final LocalDate date;
        private final ExchangeRateCalculator fxRates;
        private final InterestRate discountRate;
        private final BondShifts shifts;

        PerpetualBondPrice(
                final PerpetualBond bond,
                final C currency,
                final LocalDate date,
                final ExchangeRateCalculator fxRates,
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
        public Money<C> parValue() {
            return this.shift(bond.par(), shifts, currency, fxRates);
        }

        Money<C> couponAmount() {
            return this.shift(bond.coupons().yearlyAmount(), shifts, currency, fxRates);
        }

        Percentage annualRate() {
            return this.shift(bond.coupons().yearlyRate(), shifts).annualRate();
        }

        private final Lazy<Money<C>> cleanValue = Lazy.loadOnceNonnull(this::calculateCleanValue);

        @Override
        public Money<C> cleanValue() {
            return cleanValue.get();
        }

        @Nonnull
        private Money<C> calculateCleanValue() {
            return this.couponAmount().over(this.annualRate());
        }

        private InterestRate discountRate() {
            return this.shift(discountRate, shifts);
        }

        @Override
        public List<CashPayment<C>> cleanFlow(final Interval interval) {
            final InterestRate discountRate = PerpetualBondPrice.this.discountRate();
            final List<FixedCoupon<?>> coupons = bond.coupons().between(interval.startInclusive(), interval.endExclusive());
            return Lists.lazy(coupons.size(), index -> {
                final FixedCoupon<?> coupon = coupons.get(index);
                final Money<C> couponAmount = PerpetualBondPrice.this.shift(coupon.amount(), shifts, currency, fxRates);
                final Money<C> discountedAmount = discountRate.discount(couponAmount, date, coupon.date());
                return CashPayment.of(coupon.date(), discountedAmount);
            });
        }

        private final Lazy<Money<C>> dirtyValue = Lazy.loadOnceNonnull(this::calculateDirtyValue);

        @Override
        public Money<C> dirtyValue() {
            return dirtyValue.get();
        }

        @Nonnull
        private Money<C> calculateDirtyValue() {
            final FixedCoupon<?> priorCoupon = bond.coupons().prior(date);
            return this.discountRate().accrue(this.couponAmount(), priorCoupon.date(), date);
        }

        @Override
        public BondPrice<C> shift(final BondShifts shifts) {
            return shifts == this.shifts
                    ? this
                    : new PerpetualBondPrice<>(bond, currency, date, fxRates, discountRate, shifts);
        }

    }

}
