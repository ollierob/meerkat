package net.ollie.meerkat.security.bond;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.goat.currency.CurrencyId;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.money.interest.InterestRateId;
import net.ollie.meerkat.numeric.interest.feature.RateFeature;
import net.ollie.meerkat.security.bond.coupon.FloatingCoupon;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class FloatingRateNote extends StraightBond {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "coupon_currency")
    private CurrencyId couponCurrency;

    @XmlAttribute(name = "spread")
    private Percentage spread;

    @XmlElementRef(name = "reference_rate")
    private InterestRateId referenceRate;

    @XmlElement(name = "coupon_date")
    private List<LocalDate> couponDates;

    @XmlElementRef(name = "coupon_feature")
    private Set<? extends RateFeature> features;

    @Deprecated
    FloatingRateNote() {
    }

    @Override
    public FloatingRateNoteCoupons<?> coupons() {
        return new FloatingRateNoteCoupons<>(couponCurrency);
    }

    @Override
    public <R> R handleWith(final Bond.Handler<R> handler) {
        return handler.handle(this);
    }

    public class FloatingRateNoteCoupons<C extends CurrencyId>
            extends StraightBondCoupons<FloatingCoupon> {

        private final C currency;

        FloatingRateNoteCoupons(final C currency) {
            this.currency = currency;
        }

        @Override
        public FloatingCoupon get(final int index) {
            return new FloatingCoupon(couponDates.get(index), spread, referenceRate, features);
        }

        @Override
        public int size() {
            return couponDates.size();
        }

        @Override
        public boolean isEmpty() {
            return couponDates.isEmpty();
        }

        @Override
        public C currencyId() {
            return currency;
        }

    }

}
