package net.ollie.meerkat.security.bond;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.bond.coupon.BondCoupon;
import net.ollie.meerkat.security.bond.coupon.BondCoupons;
import net.ollie.meerkat.security.bond.dates.ConvertibleBondDates;
import net.ollie.meerkat.security.equity.Stock;
import net.ollie.meerkat.utils.collections.FiniteSequence;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class ConvertibleBond extends AbstractBond {

    @XmlElement(name = "dates", required = true)
    private ConvertibleBondDates dates;

    @XmlElementRef(name = "coupon")
    private List<BondCoupon> coupons;

    @XmlElement(name = "stock", required = true)
    private Stock stock;

    @XmlAttribute(name = "conversion_ratio", required = true)
    private BigDecimal conversionRatio;

    @Deprecated
    ConvertibleBond() {
    }

    @Override
    public ConvertibleBondDates dates() {
        return dates;
    }

    @Override
    public ConvertibleBondCoupons coupons() {
        return new ConvertibleBondCoupons();
    }

    @Nonnull
    public BigDecimal conversionRatio() {
        return conversionRatio;
    }

    @Nonnull
    public Money<?> conversionPrice() {
        return this.par().over(this.conversionRatio());
    }

    @Override
    public <R> R handleWith(final Bond.Handler<R> handler) {
        return handler.handle(this);
    }

    @XmlTransient
    public class ConvertibleBondCoupons
            extends FiniteSequence<BondCoupon>
            implements BondCoupons.Finite<BondCoupon> {

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
