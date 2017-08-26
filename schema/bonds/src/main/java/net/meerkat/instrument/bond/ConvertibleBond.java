package net.meerkat.instrument.bond;

import java.math.BigDecimal;
import java.util.AbstractList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import net.meerkat.money.Money;
import net.meerkat.instrument.bond.coupon.BondCoupon;
import net.meerkat.instrument.bond.coupon.BondCoupons;
import net.meerkat.instrument.bond.dates.ConvertibleBondDates;
import net.meerkat.instrument.equity.Stock;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.bond.call.BondCall;
import net.meerkat.issuer.IssuerId;

/**
 *
 * @author Ollie
 */
public class ConvertibleBond extends AbstractBond {

    private static final long serialVersionUID = 1L;

    private final ConvertibleBondDates dates;
    private final List<BondCoupon> coupons;
    private final Stock stock;
    private final BigDecimal conversionRatio;

    public ConvertibleBond(ConvertibleBondDates dates, List<BondCoupon> coupons, Stock stock, BigDecimal conversionRatio, String name, InstrumentIds identifiers, Money<?> par, BondCall call, IssuerId issuer) {
        super(name, identifiers, par, call, issuer);
        this.dates = dates;
        this.coupons = coupons;
        this.stock = stock;
        this.conversionRatio = conversionRatio;
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
            extends AbstractList<BondCoupon>
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

        @Override
        public CurrencyId currencyId() {
            return coupons.get(0).currencyId(); //FIXME
        }

    }

}
