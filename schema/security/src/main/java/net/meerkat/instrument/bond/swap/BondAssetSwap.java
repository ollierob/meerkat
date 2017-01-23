package net.meerkat.instrument.bond.swap;

import java.util.AbstractList;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.bond.BondDerivative;
import net.meerkat.instrument.bond.FixedCouponBond;
import net.meerkat.instrument.bond.FixedCouponBond.FixedCouponBondCoupons;
import net.meerkat.instrument.bond.coupon.FixedRateCoupon;
import net.meerkat.instrument.derivative.swap.AbstractSwap;
import net.meerkat.issuer.IssuerId;
import net.meerkat.numeric.interest.InterestRateId;
import net.ollie.meerkat.utils.collections.sequence.FiniteSequence;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class BondAssetSwap
        extends AbstractSwap
        implements BondDerivative<FixedCouponBond> {

    @XmlElementRef(name = "underlying")
    private FixedCouponBond underlying;

    @XmlElementRef(name = "reference_rate")
    private InterestRateId referenceRate;

    @Deprecated
    BondAssetSwap() {
    }

    public BondAssetSwap(
            final String name,
            final InstrumentIds identifiers,
            final IssuerId issuer,
            final FixedCouponBond underlying,
            final InterestRateId referenceRate) {
        super(name, identifiers, issuer);
        this.underlying = underlying;
        this.referenceRate = referenceRate;
    }

    @Override
    public FixedCouponBond underlying() {
        return underlying;
    }

    @Override
    public BondAssetSwapLegs legs() {
        return new BondAssetSwapLegs();
    }

    @Override
    public <R> R handleWith(final BondDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

    public class BondAssetSwapLegs extends AbstractList<BondAssetSwapLeg> implements FiniteSequence<BondAssetSwapLeg> {

        private final FixedCouponBondCoupons coupons = underlying.coupons();

        @Override
        public BondAssetSwapLeg get(final int index) {
            final FixedRateCoupon coupon = coupons.get(index);
            return new BondAssetSwapLeg(coupon, referenceRate);
        }

        @Override
        public int size() {
            return coupons.size();
        }

    }

}
