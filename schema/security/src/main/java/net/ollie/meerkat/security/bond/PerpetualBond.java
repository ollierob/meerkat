package net.ollie.meerkat.security.bond;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.numeric.interest.FixedInterestRate;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.bond.coupon.BondCoupons;
import net.ollie.meerkat.security.bond.coupon.FixedCoupon;
import net.ollie.meerkat.security.bond.dates.PerpetualBondDates;
import net.ollie.meerkat.utils.collections.Sequence;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class PerpetualBond extends AbstractBond {

    @XmlAttribute(name = "yearly_frequency")
    private int yearlyFrequency;

    @XmlElementRef(name = "coupon_amount")
    private Money<?> couponAmount;

    @XmlElementRef(name = "coupon_rate")
    private FixedInterestRate couponRate;

    @XmlElementRef(name = "dates")
    private PerpetualBondDates dates;

    @Deprecated
    PerpetualBond() {
    }

    @Override
    public PerpetualBondNominal nominal() {
        return new PerpetualBondNominal();
    }

    @Override
    public PerpetualBondCoupons coupons() {
        return new PerpetualBondCoupons();
    }

    @Override
    public PerpetualBondDates dates() {
        return dates;
    }

    @Override
    public <R> R handleWith(final Bond.Handler<R> handler) {
        return handler.handle(this);
    }

    public class PerpetualBondNominal implements BondNominal {

        @Override
        public Money<?> par() {
            return PerpetualBond.this.par();
        }

    }

    public class PerpetualBondCoupons implements BondCoupons<FixedCoupon> {

        public FixedInterestRate recurringRate() {
            return couponRate;
        }

        public Money<?> recurringAmount() {
            return couponAmount;
        }

        @Override
        public boolean hasFloatingRateCoupon() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public int yearlyFrequency() {
            return yearlyFrequency;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public Sequence<FixedCoupon> onOrAfter(final LocalDate time) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public FixedCoupon prior(final LocalDate current) {
            throw new UnsupportedOperationException(); //TODO
        }

    }

}
