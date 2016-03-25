package net.ollie.meerkat.security.bond;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.bond.call.BondCall;
import net.ollie.meerkat.security.bond.coupon.FloatingCoupon;
import net.ollie.meerkat.security.bond.dates.MaturingBondDates;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class FloatingRateNote extends StraightBond {

    @XmlElementRef(name = "coupon")
    private List<FloatingCoupon> coupons;

    @Deprecated
    FloatingRateNote() {
    }

    public FloatingRateNote(
            final String name,
            final Money<?> par,
            final MaturingBondDates dates,
            final List<FloatingCoupon> coupons,
            final BondCall call) {
        super(name, par, dates, call);
        this.coupons = coupons;
    }

    @Override
    public FloatingRateNoteCoupons coupons() {
        return new FloatingRateNoteCoupons();
    }

    @Override
    public FloatingRateNote strip() {
        return new FloatingRateNote(this.name(), this.par(), this.dates(), Collections.emptyList(), this.call().orElse(null));
    }

    @Override
    public <R> R handleWith(final Bond.Handler<R> handler) {
        return handler.handle(this);
    }

    public class FloatingRateNoteCoupons extends StraightBondCoupons<FloatingCoupon> {

        @Override
        public FloatingCoupon get(final int index) {
            return coupons.get(index);
        }

        @Override
        public int size() {
            return coupons.size();
        }

        @Override
        public boolean isEmpty() {
            return coupons.isEmpty();
        }

    }

}
