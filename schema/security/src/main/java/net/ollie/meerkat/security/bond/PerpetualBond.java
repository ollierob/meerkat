package net.ollie.meerkat.security.bond;

import java.time.LocalDate;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.bond.coupon.BondCoupon;
import net.ollie.meerkat.security.bond.coupon.BondCoupons;
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

    public class PerpetualBondCoupons implements BondCoupons<BondCoupon> {

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
        public Sequence<BondCoupon> onOrAfter(LocalDate time) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public List<BondCoupon> between(LocalDate start, LocalDate end) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

}
