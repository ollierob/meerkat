package net.meerkat.instrument.bond;

import net.coljate.list.List;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.bond.call.BondCall;
import net.meerkat.instrument.bond.coupon.BondCoupons;
import net.meerkat.instrument.bond.coupon.FixedCoupon;
import net.meerkat.instrument.bond.dates.PerpetualBondDates;
import net.meerkat.issuer.IssuerId;
import net.meerkat.money.Money;
import net.meerkat.money.interest.fixed.FixedInterestRate;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 *
 * @author ollie
 */
public class PerpetualBond<C extends CurrencyId> extends AbstractBond {

    private final int yearlyFrequency;
    private final Money<C> couponAmount;
    private final FixedInterestRate couponRate;
    private final PerpetualBondDates dates;

    private transient PerpetualBondCoupons coupons;

    public PerpetualBond(
            final String name,
            final InstrumentIds identifiers,
            final Money<?> par,
            final BondCall call,
            final IssuerId issuer,
            final int yearlyFrequency,
            final Money<C> couponAmount,
            final FixedInterestRate couponRate,
            final PerpetualBondDates dates) {
        super(name, identifiers, par, call, issuer);
        this.yearlyFrequency = yearlyFrequency;
        this.couponAmount = couponAmount;
        this.couponRate = couponRate;
        this.dates = dates;
    }

    @Override
    public PerpetualBondCoupons coupons() {
        return coupons == null ? (coupons = new PerpetualBondCoupons(couponAmount)) : coupons;
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

    public class PerpetualBondCoupons
            implements BondCoupons<FixedCoupon<?>>, HasCurrencyId {

        private final Money<C> coupon;

        PerpetualBondCoupons(final Money<C> coupon) {
            this.coupon = coupon;
        }

        public FixedInterestRate yearlyRate() {
            return couponRate;
        }

        public Money<C> yearlyAmount() {
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
        @Deprecated
        public boolean isEmpty() {
            return false;
        }

        @Override
        public FixedCoupon<?> latestBefore(final LocalDate current) {
            throw new UnsupportedOperationException(); //TODO
        }

        @Override
        public C currencyId() {
            return coupon.currencyId();
        }

        @Override
        public BondCoupons<FixedCoupon<?>> filter(Predicate<? super FixedCoupon<?>> predicate) {
            throw new UnsupportedOperationException(); //TODO
        }

        @Override
        public boolean isFinite() {
            return false;
        }

        @Override
        public FixedCoupon<?> getIfPresent(final Object key) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean contains(final Object object) {
            throw new UnsupportedOperationException(); //TODO
        }

        @Override
        public List<FixedCoupon<?>> allBetween(LocalDate startInclusive, LocalDate endExclusive) {
            throw new UnsupportedOperationException(); //TODO
        }

        @Override
        public BondCoupons<FixedCoupon<?>> onOrAfter(LocalDate start) {
            throw new UnsupportedOperationException(); //TODO
        }

    }

}
