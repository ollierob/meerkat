package net.ollie.meerkat.security.bond;

import java.time.LocalDate;
import java.util.Optional;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.interest.FixedInterestRate;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.security.bond.coupon.BondCoupons;
import net.ollie.meerkat.security.bond.coupon.FixedRateCoupon;
import net.ollie.meerkat.security.bond.dates.PerpetualBondDates;

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

    private transient PerpetualBondCoupons coupons;

    @Override
    public PerpetualBondCoupons coupons() {
        return coupons == null ? (coupons = new PerpetualBondCoupons()) : coupons;
    }

    @Override
    public PerpetualBondDates dates() {
        return dates;
    }

    @Override
    public <R> R handleWith(final Bond.Handler<R> handler) {
        return handler.handle(this);
    }

    public class PerpetualBondCoupons implements BondCoupons<FixedRateCoupon<?>> {

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
        public boolean isEmpty() {
            return false;
        }

        @Override
        public PerpetualBondCoupons onOrAfter(final LocalDate time) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public FixedRateCoupon<?> prior(final LocalDate current) {
            throw new UnsupportedOperationException(); //TODO
        }

        @Override
        public Optional<Integer> count() {
            return Optional.empty();
        }

        @Override
        public CurrencyId currencyId() {
            return couponAmount.currencyId();
        }

    }

}
