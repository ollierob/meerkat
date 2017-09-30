package net.meerkat.pricing.bond;

import java.time.LocalDate;
import java.util.SortedMap;
import java.util.TreeMap;

import net.coljate.list.List;
import net.meerkat.money.fx.ExchangeRatesProvider;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.bond.FixedCouponBond;
import net.meerkat.instrument.bond.FixedCouponBond.FixedCouponBondCoupons;
import net.meerkat.instrument.bond.coupon.FixedCoupon;
import net.meerkat.instrument.cash.CashPayment;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRates;
import net.meerkat.money.fx.exception.ExchangeRateException;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.exception.InterestRateException;
import net.meerkat.money.interest.interpolation.InterestRateInterpolator;
import net.meerkat.pricing.bond.shifts.BondShifts;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.years.Years;
import net.meerkat.money.interest.DiscountRateProvider;

/**
 * Prices fixed coupon bonds purely based on their coupon rate.
 *
 * @author ollie
 */
public class ZeroSpreadFixedCouponBondPricer implements BondPricer<LocalDate, FixedCouponBond> {

    private final ExchangeRatesProvider<LocalDate> exchangeRatesProvider;
    private final DiscountRateProvider<LocalDate> discountRatesProvider;
    private final InterestRateInterpolator interestRateInterpolator;

    public ZeroSpreadFixedCouponBondPricer(
            final ExchangeRatesProvider<LocalDate> exchangeRatesProvider,
            final DiscountRateProvider<LocalDate> discountRatesProvider,
            final InterestRateInterpolator interestRateInterpolator) {
        this.exchangeRatesProvider = exchangeRatesProvider;
        this.discountRatesProvider = discountRatesProvider;
        this.interestRateInterpolator = interestRateInterpolator;
    }

    @Override
    public <C extends CurrencyId> BondPrice.Shiftable<C> price(
            final LocalDate date,
            final FixedCouponBond bond,
            final C currency,
            final BondShifts shifts) {
        return this.price(date, bond.nominal(), bond.coupons(), currency, shifts);
    }

    public <P extends CurrencyId, Z extends CurrencyId, C extends CurrencyId> BondPrice.Shiftable<C> price(
            final LocalDate date,
            final CashPayment<P> par,
            final FixedCouponBondCoupons<Z> coupons,
            final C currency,
            final BondShifts shifts) {
        try {
            final ExchangeRates fxRates = exchangeRatesProvider.require(date);
            final InterestRate discountRate = discountRatesProvider.require(date, currency);
            return new ZeroSpreadFixedCouponBondPrice<>(
                    date,
                    par,
                    coupons,
                    fxRates,
                    discountRate,
                    interestRateInterpolator,
                    shifts);
        } catch (final ExchangeRateException | InterestRateException ex) {
            throw new BondPriceException(ex);
        }
    }

    private static final class ZeroSpreadFixedCouponBondPrice<C extends CurrencyId>
            implements BondPrice.Shiftable<C> {

        private final LocalDate valuationDate;
        private final CashPayment<?> par;
        private final FixedCouponBondCoupons<?> coupons;
        private final ExchangeRates fxRates;
        private final InterestRate discountRate;
        private final InterestRateInterpolator interestRateInterpolator;
        private final BondShifts shifts;

        ZeroSpreadFixedCouponBondPrice(
                final LocalDate valuationDate,
                final CashPayment<?> par,
                final FixedCouponBondCoupons<?> coupons,
                final ExchangeRates fxRates,
                final InterestRate discountRate,
                final InterestRateInterpolator interestRateInterpolator,
                final BondShifts shifts) {
            this.valuationDate = valuationDate;
            this.par = par;
            this.coupons = coupons;
            this.shifts = shifts;
            this.fxRates = fxRates;
            this.discountRate = discountRate;
            this.interestRateInterpolator = interestRateInterpolator;
        }

        ExchangeRates fxRates() {
            return shifts.shift(fxRates);
        }

        @Override
        public Money<C> par() {
            return this.fxRates().convert(par.paymentAmount(), this.currencyId());
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
                    .collect(List.collector());
        }

        @Override
        public Money<C> accruedInterest() {
            final FixedCoupon<?> prior = coupons.prior(valuationDate);
            final Years years = prior.accrual().yearsBetween(valuationDate, valuationDate);
            final Money<C> couponAmount = this.fxRates().convert(prior.paymentAmount(), this.currencyId());
            return couponAmount.times(years.decimalValue());
        }

        @Override
        public Money<C> dirty() {
            return this.clean().plus(this.accruedInterest());
        }

        @Override
        public BondPrice.Shiftable<C> shift(final BondShifts shifts) {
            return new ZeroSpreadFixedCouponBondPrice<>(valuationDate, par, coupons, fxRates, discountRate, interestRateInterpolator, shifts);
        }

        LocalDate maturity() {
            return par.paymentDate();
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
            final ExchangeRates fxRates = this.fxRates();
            final List<? extends FixedCoupon<?>> coupons = this.coupons.between(startInclusive, endExclusive);
            final SortedMap<LocalDate, Money<C>> flow = new TreeMap<>();
            for (final FixedCoupon<?> coupon : coupons) {
                final Money<C> money = fxRates.convert(coupon.paymentAmount(), this.currencyId());
                flow.compute(coupon.paymentDate(), (date, current) -> current == null ? money : money.plus(current));
            }
            return flow;
        }

        @Override
        public Percentage yieldToMaturity() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

}
