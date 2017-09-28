package net.meerkat.instrument.bond;

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
public abstract class StraightBond extends AbstractBond {

    private final MaturingBondDates dates;

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

    public abstract class StraightBondCoupons<C extends BondCoupon> implements BondCoupons.Finite<C> {

    }

}
