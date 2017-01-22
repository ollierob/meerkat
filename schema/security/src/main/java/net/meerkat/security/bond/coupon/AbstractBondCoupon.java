package net.meerkat.security.bond.coupon;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author ollie
 */
public abstract class AbstractBondCoupon implements BondCoupon {

    @XmlAttribute(name = "date")
    private LocalDate paymentDate;

    @Deprecated
    protected AbstractBondCoupon() {
    }

    protected AbstractBondCoupon(final LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public LocalDate paymentDate() {
        return paymentDate;
    }

}
