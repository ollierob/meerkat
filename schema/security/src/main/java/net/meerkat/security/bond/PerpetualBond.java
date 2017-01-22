package net.meerkat.security.bond;

import java.time.LocalDate;
import java.util.Optional;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.money.Money;
import net.meerkat.money.currency.Currency;
import net.meerkat.money.currency.HasCurrency;
import net.meerkat.money.interest.fixed.FixedInterestRate;
import net.meerkat.security.bond.coupon.BondCoupons;
import net.meerkat.security.bond.coupon.FixedRateCoupon;
import net.meerkat.security.bond.dates.PerpetualBondDates;
import net.ollie.meerkat.utils.collections.sequence.FiniteSequence;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class PerpetualBond extends AbstractBond {

    private static final long serialVersionUID = 1L;

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

    private transient PerpetualBondCoupons<?> coupons;

    @Override
    public PerpetualBondCoupons<?> coupons() {
        return coupons == null ? (coupons = new PerpetualBondCoupons<>(couponAmount)) : coupons;
    }

    @Override
    public PerpetualBondDates dates() {
        return dates;
    }

    @Override
    @Deprecated
    public boolean isZeroCoupon() {
        return false;
    }

    @Override
    public <R> R handleWith(final Bond.Handler<R> handler) {
        return handler.handle(this);
    }

    public class PerpetualBondCoupons<C extends Currency>
            implements BondCoupons<FixedRateCoupon<?>>, HasCurrency {

        private final Money<C> coupon;

        PerpetualBondCoupons(final Money<C> coupon) {
            this.coupon = coupon;
        }

        public FixedInterestRate yearlyRate() {
            return couponRate;
        }

        public Money<?> yearlyAmount() {
            return couponAmount;
        }

        @Override
        public boolean hasFloatingRateCoupon() {
            return false;
        }

        public int yearlyFrequency() {
            return yearlyFrequency;
        }

        @Override
        public FixedRateCoupon<?> first() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        @Deprecated
        public boolean isEmpty() {
            return false;
        }

        @Override
        public PerpetualBondCoupons<C> onOrAfter(final LocalDate time) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public FiniteSequence<FixedRateCoupon<?>> between(LocalDate startInclusive, LocalDate endExclusive) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public FixedRateCoupon<?> prior(final LocalDate current) {
            throw new UnsupportedOperationException(); //TODO
        }

        @Override
        public Optional<Long> count() {
            return Optional.empty();
        }

        @Override
        public C currency() {
            return coupon.currency();
        }

    }

}
