package net.meerkat.security.bond;

import java.util.AbstractList;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;

import net.ollie.meerkat.money.Money;
import net.ollie.meerkat.identifier.security.SecurityIds;
import net.meerkat.security.bond.call.BondCall;
import net.meerkat.security.bond.coupon.BondCoupon;
import net.meerkat.security.bond.coupon.BondCoupons;
import net.meerkat.security.bond.dates.MaturingBondDates;

/**
 *
 * @author Ollie
 */
@XmlSeeAlso({FloatingRateNote.class, FixedCouponBond.class, VariableRateBond.class})
public abstract class StraightBond extends AbstractBond {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "dates")
    private MaturingBondDates dates;

    @Deprecated
    StraightBond() {
    }

    public StraightBond(
            final String name,
            final SecurityIds identifiers,
            final Money<?> par,
            final MaturingBondDates dates,
            final BondCall call) {
        super(name, identifiers, par, call);
        this.dates = dates;
    }

    @Override
    public abstract StraightBondCoupons<?> coupons();

    @Override
    public MaturingBondDates dates() {
        return dates;
    }

    @Override
    @SuppressWarnings("deprecation")
    public <R> R handleWith(final Bond.Handler<R> handler) {
        return handler.handle(this);
    }

    public abstract class StraightBondCoupons<C extends BondCoupon>
            extends AbstractList<C>
            implements BondCoupons.Finite<C> {

    }

}
