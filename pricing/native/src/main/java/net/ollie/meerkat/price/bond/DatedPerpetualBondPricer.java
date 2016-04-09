package net.ollie.meerkat.price.bond;

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
import net.ollie.meerkat.security.bond.coupon.FixedRateCoupon;
import net.ollie.meerkat.security.fx.CashPayment;
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
    public <C extends CurrencyId> BondPrice.Shiftable<C> price(
            final LocalDate date,
            final PerpetualBond bond,
            final C currency) {
        final ExchangeRateCalculator exchangeRates = getExchangeRates.apply(date);
        final InterestRate discountRate = getDiscountRates.apply(date, currency);
        return new PerpetualBondPrice<>(bond, currency, date, exchangeRates, discountRate, BondShifts.none());
    }

    private static final class PerpetualBondPrice<C extends CurrencyId>
            implements BondPrice.Shiftable<C>, ExchangeRateShifter, InterestRateShifter {

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
        public List<CashPayment<C>> cleanFlow(final LocalDate start, final LocalDate end) {
            final InterestRate discountRate = PerpetualBondPrice.this.discountRate();
            final List<FixedRateCoupon<?>> coupons = bond.coupons().between(start, end);
            return Lists.lazy(coupons.size(), index -> {
                final FixedRateCoupon<?> coupon = coupons.get(index);
                final Money<C> couponAmount = PerpetualBondPrice.this.shift(coupon.amount(), shifts, currency, fxRates);
                final Money<C> discountedAmount = discountRate.discount(couponAmount, date, coupon.date());
                return CashPayment.of(coupon.date(), discountedAmount);
            });
        }

        private final Lazy<Money<C>> accruedInterest = Lazy.loadOnceNonnull(this::calculateAccuredInterest);

        @Override
        public Money<C> accruedInterest() {
            return accruedInterest.get();
        }

        @Override
        public Money<C> dirtyValue() {
            return this.cleanValue().plus(this.accruedInterest());
        }

        @Nonnull
        private Money<C> calculateAccuredInterest() {
            final FixedRateCoupon<?> priorCoupon = bond.coupons().prior(date);
            final Money<C> priorAmount = this.couponAmount();
            return this.discountRate().accrue(priorAmount, priorCoupon.date(), date).minus(priorAmount);
        }

        @Override
        public BondPrice.Shiftable<C> shift(final BondShifts shifts) {
            return shifts == this.shifts
                    ? this
                    : new PerpetualBondPrice<>(bond, currency, date, fxRates, discountRate, shifts);
        }

        @Override
        public C currencyId() {
            return currency;
        }

    }

}
