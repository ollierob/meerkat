package net.meerkat.pricing.bond;

import java.time.LocalDate;
import java.util.SortedMap;
import java.util.TreeMap;

import net.coljate.list.List;
import net.meerkat.Explained;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.bond.FixedCouponBond;
import net.meerkat.instrument.bond.FixedCouponBond.FixedCouponBondCoupons;
import net.meerkat.instrument.bond.coupon.FixedCoupon;
import net.meerkat.instrument.cash.CashPayment;
import net.meerkat.money.Money;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.money.fx.ExchangeRates;
import net.meerkat.money.fx.ExchangeRatesProvider;
import net.meerkat.money.fx.exception.ExchangeRateException;
import net.meerkat.money.interest.DiscountRateProvider;
import net.meerkat.money.interest.InterestRate;
import net.meerkat.money.interest.exception.InterestRateException;
import net.meerkat.money.interest.interpolation.InterestRateInterpolator;
import net.meerkat.pricing.bond.shifts.BondShifts;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.suppliers.lazy.Lazy;
import net.ollie.goat.temporal.date.years.Years;

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
            final C currencyId,
            final BondShifts shifts) {
        try {
            final ExchangeRates fxRates = exchangeRatesProvider.require(date);
            final ExchangeRate<P, C> parFxRate = fxRates.rate(par.currencyId(), currencyId);
            final ExchangeRate<Z, C> couponFxRate = fxRates.rate(coupons.currencyId(), currencyId);
            final InterestRate discountRate = discountRatesProvider.require(date, currencyId);
            return new ZeroSpreadFixedCouponBondPrice<>(
                    date,
                    par,
                    parFxRate,
                    coupons,
                    couponFxRate,
                    discountRate,
                    interestRateInterpolator,
                    shifts);
        } catch (final ExchangeRateException | InterestRateException ex) {
            throw new BondPriceException("Data error when pricing bond!", ex);
        }
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
                final ExchangeRate<P, C> parFxRate,
                final FixedCouponBondCoupons<Z> coupons,
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

        ExchangeRate<P, C> parFxRate() {
            return shifts.shift(parFxRate);
        }

        ExchangeRate<Z, C> couponFxRate() {
            return shifts.shift(couponFxRate);
        }

        @Override
        public Money<C> par() {
            return this.parFxRate().convert(par.paymentAmount());
        }

        private final Lazy<Explained<Money<C>>> clean = Lazy.loadOnce(() -> {
            final ZeroSpreadFixedCouponBondPrice<?, ?, C> price = this;
            final InterestRate discountRate = price.discountRate();
            final Money<C> presentParValue = price.presentParValue(discountRate);
            final SortedMap<LocalDate, Money<C>> cleanFlow = price.cleanFlow(discountRate);
            return new Explained<>(
                    cleanFlow.values().stream().reduce(presentParValue, Money::plus),
                    new ExplanationBuilder().put("discount rate", discountRate));
        });

        @Override
        public Money<C> clean() {
            return clean.get().value();
        }

        private final Lazy<Explained<Money<C>>> accrued = Lazy.loadOnce(() -> {
            final ZeroSpreadFixedCouponBondPrice<?, Z, C> price = this;
            final FixedCoupon<Z> prior = price.coupons.prior(price.valuationDate);
            final Years years = prior.accrual().yearsBetween(prior.paymentDate(), price.valuationDate);
            final Money<C> couponAmount = this.couponFxRate().convert(prior.paymentAmount());
            return new Explained<>(
                    couponAmount.times(years.decimalValue()),
                    new ExplanationBuilder().put("prior coupon", prior));
        });

        @Override
        public Money<C> accruedInterest() {
            return accrued.get().value();
        }

        @Override
        public Money<C> dirty() {
            return this.clean().plus(this.accruedInterest());
        }

        @Override
        public BondPrice.Shiftable<C> shift(final BondShifts shifts) {
            return new ZeroSpreadFixedCouponBondPrice<>(valuationDate, par, parFxRate, coupons, couponFxRate, discountRate, interestRateInterpolator, shifts);
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

        SortedMap<LocalDate, Money<C>> cleanFlow(final InterestRate discountRate) {
            final ExchangeRate<Z, C> couponFxRates = this.couponFxRate();
            final List<? extends FixedCoupon<Z>> coupons = this.coupons.between(valuationDate, this.maturity());
            final SortedMap<LocalDate, Money<C>> flow = new TreeMap<>();
            for (final FixedCoupon<Z> coupon : coupons) {
                final Money<C> money = couponFxRates.convert(coupon.paymentAmount());
                flow.compute(coupon.paymentDate(), (date, current) -> current == null ? money : money.plus(current));
            }
            return flow;
        }

        @Override
        public Percentage yieldToMaturity() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public ExplanationBuilder explain() {
            return this.explanationBuilder()
                    .put("valuation date", valuationDate)
                    .put("par", par)
                    .put("coupons", coupons)
                    .put("clean price", clean.get())
                    .put("accrued interest", accrued.get())
                    .put("dirty price", this.dirty());
        }

    }

}
