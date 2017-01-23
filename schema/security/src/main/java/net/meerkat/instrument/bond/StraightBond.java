package net.meerkat.instrument.bond;

import java.util.AbstractList;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.bond.call.BondCall;
import net.meerkat.instrument.bond.coupon.BondCoupon;
import net.meerkat.instrument.bond.coupon.BondCoupons;
import net.meerkat.instrument.bond.dates.MaturingBondDates;
import net.meerkat.issuer.IssuerId;
import net.meerkat.money.Money;

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
            final InstrumentIds identifiers,
            final Money<?> par,
            final MaturingBondDates dates,
            final BondCall call,
            final IssuerId issuer) {
        super(name, identifiers, par, call, issuer);
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
