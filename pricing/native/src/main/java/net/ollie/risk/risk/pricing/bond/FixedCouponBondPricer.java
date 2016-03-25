package net.ollie.risk.risk.pricing.bond;

import java.time.LocalDate;
import java.util.List;

import net.ollie.meerkat.calculate.price.bond.BondPrice;
import net.ollie.meerkat.calculate.price.bond.BondPricer;
import net.ollie.meerkat.numeric.interest.FixedInterestRate;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.bond.FixedCouponBond;
import net.ollie.meerkat.security.bond.FixedCouponBond.FixedCouponBondCoupons;
import net.ollie.meerkat.security.bond.StraightBond.StraightBondNominal;
import net.ollie.meerkat.security.bond.coupon.FixedCoupon;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.calculate.price.bond.BondShifts;

/**
 *
 * @author ollie
 */
public class FixedCouponBondPricer implements BondPricer<LocalDate, FixedCouponBond> {

    @Override
    public BondPrice price(final LocalDate date, final FixedCouponBond bond, final BondShifts shifts) {
        final StraightBondNominal nominal = bond.nominal();
        final FixedCouponBondCoupons coupons = bond.coupons();
        final LocalDate matures = bond.dates().matures();
        final Money par = shifts.shiftPrice(nominal.par());
        return coupons.isEmpty()
                ? this.priceZeroCoupon(date, matures, par, bond.couponRate())
                : this.priceCoupons(date, matures, par, coupons);
    }

    protected BondPrice priceZeroCoupon(
            final LocalDate date,
            final LocalDate maturity,
            final Money par,
            final FixedInterestRate rate) {
        final Money price = rate.discount(par, date, maturity);
        return new ZeroCouponBondPrice(par, price, price);
    }

    protected BondPrice priceCoupons(
            final LocalDate date,
            final LocalDate maturity,
            final Money par,
            final List<FixedCoupon> coupons) {
        throw new UnsupportedOperationException();
    }

    private final class ZeroCouponBondPrice implements BondPrice {

        private final Money par, clean, dirty;

        public ZeroCouponBondPrice(final Money par, final Money clean, final Money dirty) {
            this.par = par;
            this.clean = clean;
            this.dirty = dirty;
        }

        @Override
        public Money par() {
            return par;
        }

        @Override
        public Money cleanPrice() {
            return clean;
        }

        @Override
        public Percentage cleanPercentage() {
            return new Percentage(par.amount().doubleValue() / clean.amount().doubleValue());
        }

        @Override
        public Money dirtyPrice() {
            return dirty;
        }

        @Override
        public Percentage dirtyPercentage() {
            return new Percentage(par.amount().doubleValue() / dirty.amount().doubleValue());
        }

    }

}
