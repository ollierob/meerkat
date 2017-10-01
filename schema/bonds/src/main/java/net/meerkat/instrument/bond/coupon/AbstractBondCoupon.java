package net.meerkat.instrument.bond.coupon;

import java.time.LocalDate;

/**
 *
 * @author ollie
 */
public abstract class AbstractBondCoupon implements BondCoupon {

    private final LocalDate paymentDate;

    protected AbstractBondCoupon(final LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public LocalDate paymentDate() {
        return paymentDate;
    }

}
