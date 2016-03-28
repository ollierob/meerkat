package net.ollie.meerkat.security.bond.swap;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.numeric.interest.InterestRateId;
import net.ollie.meerkat.security.bond.BondDerivative;
import net.ollie.meerkat.security.bond.FixedCouponBond;
import net.ollie.meerkat.security.bond.FixedCouponBond.FixedCouponBondCoupons;
import net.ollie.meerkat.security.bond.coupon.FixedCoupon;
import net.ollie.meerkat.security.derivative.swap.AbstractSwap;
import net.ollie.meerkat.utils.collections.FiniteSequence;

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

    public BondAssetSwap(final String name, final FixedCouponBond underlying, final InterestRateId referenceRate) {
        super(name);
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

    public class BondAssetSwapLegs extends FiniteSequence<BondAssetSwapLeg> {

        private final FixedCouponBondCoupons coupons = underlying.coupons();

        @Override
        public BondAssetSwapLeg get(final int index) {
            final FixedCoupon coupon = coupons.get(index);
            return new BondAssetSwapLeg(coupon, referenceRate);
        }

        @Override
        public int size() {
            return coupons.size();
        }

    }

}
