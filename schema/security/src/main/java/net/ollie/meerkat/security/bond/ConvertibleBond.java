package net.ollie.meerkat.security.bond;

import java.util.AbstractList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

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

    @XmlElementRef(name = "par")
    private Money par;

    @XmlElementRef(name = "stock")
    private Stock stock;

    @XmlElement(name = "dates")
    private MaturingBondDates dates;

    @XmlElementRef(name = "coupon")
    private List<BondCoupon> coupons;

    @XmlElementRef(name = "call")
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

    public class ConvertibleBondNominal implements BondNominal {

        @Override
        public Money par() {
            return par;
        }

        public Stock stock() {
            return stock;
        }

    }

    public class ConvertibleBondCoupons extends AbstractList<BondCoupon> implements BondCoupons.Finite<BondCoupon> {

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
