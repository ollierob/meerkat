package net.ollie.meerkat.security.bond;

import java.time.LocalDate;
import java.util.Optional;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.identifier.currency.HasCurrencyId;
import net.ollie.meerkat.numeric.interest.FixedInterestRate;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.bond.coupon.BondCoupons;
import net.ollie.meerkat.security.bond.coupon.FixedRateCoupon;
import net.ollie.meerkat.security.bond.dates.PerpetualBondDates;
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
    public <R> R handleWith(final Bond.Handler<R> handler) {
        return handler.handle(this);
    }

    public class PerpetualBondCoupons<C extends CurrencyId>
            implements BondCoupons<FixedRateCoupon<?>>, HasCurrencyId {

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
        public C currencyId() {
            return coupon.currencyId();
        }

    }

}
