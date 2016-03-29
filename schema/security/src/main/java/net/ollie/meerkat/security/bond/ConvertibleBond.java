package net.ollie.meerkat.security.bond;

import java.util.AbstractList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.bond.call.BondCall;
import net.ollie.meerkat.security.bond.coupon.BondCoupon;
import net.ollie.meerkat.security.bond.coupon.BondCoupons;
import net.ollie.meerkat.security.bond.dates.MaturingBondDates;
import net.ollie.meerkat.security.equity.Stock;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class ConvertibleBond extends AbstractBond {

    @XmlElementRef(name = "par", required = true)
    private Money<?> par;

    @XmlElementRef(name = "stock", required = true)
    private Stock stock;

    @XmlAttribute(name = "mandatory")
    private boolean mandatory;

    @XmlElement(name = "dates", required = true)
    private MaturingBondDates dates;

    @XmlElementRef(name = "coupon")
    private List<BondCoupon> coupons;

    @XmlElementRef(name = "call", required = false)
    private BondCall call;

    @Deprecated
    ConvertibleBond() {
    }

    @Override
    public MaturingBondDates dates() {
        return dates;
    }

    @Override
    public ConvertibleBondNominal nominal() {
        return new ConvertibleBondNominal();
    }

    @Override
    public ConvertibleBondCoupons coupons() {
        return new ConvertibleBondCoupons();
    }

    @Override
    public <R> R handleWith(final Bond.Handler<R> handler) {
        return handler.handle(this);
    }

    @XmlTransient
    public class ConvertibleBondNominal implements BondNominal {

        private ConvertibleBondNominal() {
        }

        @Override
        public Money<?> par() {
            return par;
        }

        public Stock stock() {
            return stock;
        }

        public boolean isMandatory() {
            return mandatory;
        }

    }

    @XmlTransient
    public class ConvertibleBondCoupons extends AbstractList<BondCoupon> implements BondCoupons.Finite<BondCoupon> {

        private ConvertibleBondCoupons() {
        }

        @Override
        public BondCoupon get(int index) {
            return coupons.get(index);
        }

        @Override
        public int size() {
            return coupons.size();
        }

    }

}
