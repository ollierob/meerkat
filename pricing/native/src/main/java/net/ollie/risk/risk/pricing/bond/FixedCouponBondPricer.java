package net.ollie.risk.risk.pricing.bond;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

import net.ollie.meerkat.calculate.fx.ExchangeRateCalculator;
import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.calculate.price.bond.BondPricer;
import net.ollie.meerkat.calculate.price.bond.BondShifts;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.interest.FixedInterestRate;
import net.ollie.meerkat.numeric.money.ExchangeRate;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.bond.FixedCouponBond;
import net.ollie.meerkat.security.bond.FixedCouponBond.FixedCouponBondCoupons;
import net.ollie.meerkat.security.bond.StraightBond.StraightBondNominal;
import net.ollie.meerkat.security.bond.coupon.FixedCoupon;

/**
 *
 * @author ollie
 */
public class FixedCouponBondPricer implements BondPricer<LocalDate, FixedCouponBond> {

    private final Function<LocalDate, ExchangeRateCalculator> exchangeRates;

    public FixedCouponBondPricer(final Function<LocalDate, ExchangeRateCalculator> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    @Override
    public <C extends CurrencyId> BondPrice<C> price(final LocalDate date, final FixedCouponBond bond, final BondShifts shifts, final C currency) {
        final StraightBondNominal nominal = bond.nominal();
        final FixedCouponBondCoupons coupons = bond.coupons();
        final LocalDate matures = bond.dates().matures();
        final Money<C> par = this.exchange(nominal.par(), currency, shifts, exchangeRates.apply(date));
        return coupons.isEmpty()
                ? this.priceZeroCoupon(date, matures, par, bond.couponRate())
                : this.priceCoupons(date, matures, par, coupons);
    }

    private <C extends CurrencyId, R extends CurrencyId> Money<C> exchange(final Money<R> par, final C reportingCurrency, final BondShifts shifts, final ExchangeRateCalculator exchangeRates) {
        final ExchangeRate<R, C> rate = shifts.shiftExchangeRate(exchangeRates.rate(par.currencyId(), reportingCurrency));
        return rate.convert(par);
    }

    protected <C extends CurrencyId> BondPrice<C> priceZeroCoupon(
            final LocalDate date,
            final LocalDate maturity,
            final Money<C> par,
            final FixedInterestRate rate) {
        final Money<C> price = rate.discount(par, date, maturity);
        return new ZeroCouponBondPrice<>(par, price, price);
    }

    protected <C extends CurrencyId> BondPrice<C> priceCoupons(
            final LocalDate date,
            final LocalDate maturity,
            final Money<C> par,
            final List<FixedCoupon> coupons) {
        throw new UnsupportedOperationException();
    }

    private final class ZeroCouponBondPrice<C extends CurrencyId> implements BondPrice<C> {

        private final Money<C> par, clean, dirty;

        public ZeroCouponBondPrice(final Money<C> par, final Money<C> clean, final Money<C> dirty) {
            this.par = par;
            this.clean = clean;
            this.dirty = dirty;
        }

        @Override
        public Money<C> par() {
            return par;
        }

        @Override
        public Money<C> cleanPrice() {
            return clean;
        }

        @Override
        public Percentage cleanPercentage() {
            return new Percentage(par.amount().doubleValue() / clean.amount().doubleValue());
        }

        @Override
        public Money<C> dirtyPrice() {
            return dirty;
        }

        @Override
        public Percentage dirtyPercentage() {
            return new Percentage(par.amount().doubleValue() / dirty.amount().doubleValue());
        }

    }

}
