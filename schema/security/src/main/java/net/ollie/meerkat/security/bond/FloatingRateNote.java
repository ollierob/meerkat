package net.ollie.meerkat.security.bond;

import java.time.LocalDate;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.interest.InterestRateId;
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

    @Deprecated
    FloatingRateNote() {
    }

    @Override
    public FloatingRateNoteCoupons<?> coupons() {
        return new FloatingRateNoteCoupons<>(couponCurrency);
    }

    @Override
    public FloatingRateNote strip() {
        throw new UnsupportedOperationException(); //TODO
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
            return new FloatingCoupon(couponDates.get(index), spread, referenceRate);
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
