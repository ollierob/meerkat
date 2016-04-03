package net.ollie.meerkat.security.bond;

import java.util.AbstractList;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.identifier.security.Isin;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.bond.call.BondCall;
import net.ollie.meerkat.security.bond.coupon.BondCoupon;
import net.ollie.meerkat.security.bond.coupon.BondCoupons;
import net.ollie.meerkat.security.bond.dates.MaturingBondDates;

/**
 *
 * @author Ollie
 */
public abstract class StraightBond extends AbstractBond {

    @XmlElementRef(name = "dates")
    private MaturingBondDates dates;

    @Deprecated
    StraightBond() {
    }

    public StraightBond(
            final String name,
            final Isin isin,
            final Money<?> par,
            final MaturingBondDates dates,
            final BondCall call) {
        super(name, isin, par, call);
        this.dates = dates;
    }

    @Override
    public abstract StraightBondCoupons<?> coupons();

    @Nonnull
    public abstract StraightBond strip();

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
