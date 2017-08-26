package net.meerkat.pricing.bond;

import java.time.LocalDate;
import java.util.List;
import static java.util.Objects.requireNonNull;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import net.meerkat.calculate.fx.ExchangeRatesProvider;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.bond.FixedCouponBond;
import net.meerkat.instrument.bond.FixedCouponBond.FixedCouponBondCoupons;
import net.meerkat.instrument.bond.coupon.FixedCoupon;
import net.meerkat.instrument.cash.CashPayment;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.fx.ExchangeRates;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.interpolation.InterestRateInterpolator;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.years.Years;

/**
 * Prices fixed coupon bonds purely based on their coupon rate.
 *
 * @author ollie
 */
public class ZeroSpreadFixedCouponBondPricer implements BondPricer<LocalDate, FixedCouponBond> {

    private final ExchangeRatesProvider<LocalDate> exchangeRatesProvider;
    private final BiFunction<? super LocalDate, ? super CurrencyId, ? extends InterestRate> getDiscountRate;
    private final InterestRateInterpolator interestRateInterpolator;

    public ZeroSpreadFixedCouponBondPricer(
            final ExchangeRatesProvider<LocalDate> exchangeRatesProvider,
            final BiFunction<LocalDate, CurrencyId, InterestRate> discountRates,
            final InterestRateInterpolator interestRateInterpolator) {
        this.exchangeRatesProvider = exchangeRatesProvider;
        this.getDiscountRate = discountRates;
        this.interestRateInterpolator = interestRateInterpolator;
    }

    @Override
    public <C extends CurrencyId> BondPrice.Shiftable<C> price(
            final LocalDate date,
            final FixedCouponBond bond,
            final C currency) {
        return this.price(date, bond.nominal(), bond.coupons(), currency);
    }

    public <P extends CurrencyId, Z extends CurrencyId, C extends CurrencyId> BondPrice.Shiftable<C> price(
            final LocalDate date,
            final CashPayment<P> par,
            final FixedCouponBondCoupons<Z> coupons,
            final C currency) {

        final ExchangeRates fxRates = exchangeRatesProvider.require(date);
        final ExchangeRate<P, C> parFxRate = fxRates.rate(par.currencyId(), currency);
        final ExchangeRate<Z, C> couponFxRate = fxRates.rate(coupons.currencyId(), currency);

        final InterestRate discountRate = requireNonNull(getDiscountRate.apply(date, currency));

        return new ZeroSpreadFixedCouponBondPrice<>(date, par, coupons, parFxRate, couponFxRate, discountRate, interestRateInterpolator, BondShifts.none());

    }

    private static final class ZeroSpreadFixedCouponBondPrice<P extends CurrencyId, Z extends CurrencyId, C extends CurrencyId>
            implements BondPrice.Shiftable<C> {

        private final LocalDate valuationDate;
        private final CashPayment<P> par;
        private final FixedCouponBondCoupons<Z> coupons;
        private final ExchangeRate<P, C> parFxRate;
        private final ExchangeRate<Z, C> couponFxRate;
        private final InterestRate discountRate;
        private final InterestRateInterpolator interestRateInterpolator;
        private final BondShifts shifts;

        ZeroSpreadFixedCouponBondPrice(
                final LocalDate valuationDate,
                final CashPayment<P> par,
                final FixedCouponBondCoupons<Z> coupons,
                final ExchangeRate<P, C> parFxRate,
                final ExchangeRate<Z, C> couponFxRate,
                final InterestRate discountRate,
                final InterestRateInterpolator interestRateInterpolator,
                final BondShifts shifts) {
            this.valuationDate = valuationDate;
            this.par = par;
            this.coupons = coupons;
            this.shifts = shifts;
            this.parFxRate = parFxRate;
            this.couponFxRate = couponFxRate;
            this.discountRate = discountRate;
            this.interestRateInterpolator = interestRateInterpolator;
        }

        @Override
        public Money<C> par() {
            return this.parFxRate().convert(par.amount());
        }

        @Override
        public Money<C> clean() {
            final InterestRate discountRate = this.discountRate();
            final Money<C> presentParValue = this.presentParValue(discountRate);
            return this.cleanFlow(valuationDate, this.maturity(), discountRate)
                    .values()
                    .stream()
                    .reduce(presentParValue, Money::plus);
        }

        @Override
        public List<CashPayment<C>> cleanFlow(final LocalDate startInclusive, final LocalDate endExclusive) {
            final InterestRate discountRate = this.discountRate();
            return this.cleanFlow(startInclusive, endExclusive, discountRate)
                    .entrySet()
                    .stream()
                    .map(entry -> CashPayment.of(entry.getKey(), entry.getValue()))
                    .collect(Collectors.toList());
        }

        @Override
        public Money<C> accruedInterest() {
            final FixedCoupon<Z> prior = coupons.prior(valuationDate);
            final Years years = prior.accrual().yearsBetween(valuationDate, valuationDate);
            final Money<C> couponAmount = this.couponFxRate().convert(prior.amount());
            return couponAmount.times(years.decimalValue());
        }

        @Override
        public Money<C> dirty() {
            return this.clean().plus(this.accruedInterest());
        }

        @Override
        public BondPrice.Shiftable<C> shift(final BondShifts shifts) {
            return new ZeroSpreadFixedCouponBondPrice<>(valuationDate, par, coupons, parFxRate, couponFxRate, discountRate, interestRateInterpolator, shifts);
        }

        ExchangeRate<P, C> parFxRate() {
            return shifts.shift(parFxRate);
        }

        ExchangeRate<Z, C> couponFxRate() {
            return shifts.shift(couponFxRate);
        }

        LocalDate maturity() {
            return par.date();
        }

        InterestRate discountRate() {
            return shifts.shift(discountRate);
        }

        Money<C> presentValue(final Money<C> amount, final LocalDate outDate, final InterestRate discountRate) {
            return discountRate.discount(amount, valuationDate, outDate, interestRateInterpolator);
        }

        Money<C> presentParValue(final InterestRate discountRate) {
            return this.presentValue(this.par(), this.maturity(), discountRate);
        }

        SortedMap<LocalDate, Money<C>> cleanFlow(LocalDate startInclusive, LocalDate endExclusive, InterestRate discountRate) {
            final ExchangeRate<Z, C> fxRate = this.couponFxRate();
            final List<FixedCoupon<Z>> coupons = this.coupons.between(startInclusive, endExclusive);
            final SortedMap<LocalDate, Money<C>> flow = new TreeMap<>();
            for (final FixedCoupon<Z> coupon : coupons) {
                final Money<C> money = fxRate.convert(coupon.amount());
                flow.compute(coupon.date(), (d, c) -> c == null ? money : money.plus(c));
            }
            return flow;
        }

        @Override
        public Percentage yieldToMaturity() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

}