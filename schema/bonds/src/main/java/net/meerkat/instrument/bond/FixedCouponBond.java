package net.meerkat.instrument.bond;

import net.coljate.list.List;
import net.coljate.list.ListIterator;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.bond.call.BondCall;
import net.meerkat.instrument.bond.coupon.FixedCoupon;
import net.meerkat.instrument.bond.dates.MaturingBondDates;
import net.meerkat.instrument.cash.CashPayment;
import net.meerkat.issuer.IssuerId;
import net.meerkat.money.Money;
import net.meerkat.money.interest.InterestRateInstrument;
import net.meerkat.money.interest.fixed.FixedInterestRate;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.Period;
import java.util.function.Predicate;

/**
 *
 * @author ollie
 */
public class FixedCouponBond
        extends StraightBond
        implements InterestRateInstrument {

    private final FixedInterestRate couponRate;
    private final Money<?> couponAmount;
    private final Period couponFrequency;
    private final List<LocalDate> couponDates;

    public FixedCouponBond(
            final String name,
            final InstrumentIds identifiers,
            final Money<?> par,
            final MaturingBondDates dates,
            final Money<?> couponAmount,
            final FixedInterestRate couponRate,
            final Period couponFrequency,
            final List<LocalDate> couponDates,
            final BondCall call,
            final IssuerId issuer) {
        super(name, identifiers, par, dates, call, issuer);
        this.couponRate = couponRate;
        this.couponAmount = couponAmount;
        this.couponFrequency = couponFrequency;
        this.couponDates = couponDates;
    }

    public CashPayment<?> nominal() {
        return CashPayment.of(this.maturity(), this.par());
    }

    @Nonnull
    public FixedInterestRate couponRate() {
        return couponRate;
    }

    @Override
    public FixedInterestRate interestRate() {
        return couponRate;
    }

    @Nonnull
    public Money<?> couponAmount() {
        return couponAmount;
    }

    public LocalDate maturity() {
        return this.dates().maturityDate();
    }

    @Override
    public FixedCouponBondCoupons<?> coupons() {
        return new FixedCouponBondCoupons<>(couponFrequency, couponAmount, coupon -> true);
    }

    @Override
    public <R> R handleWith(final Bond.Handler<R> handler) {
        return handler.handle(this);
    }

    public class FixedCouponBondCoupons<C extends CurrencyId> extends StraightBondCoupons<FixedCoupon<C>> {

        private final Money<C> couponAmount;
        private final Predicate<? super FixedCoupon<?>> predicate;

        FixedCouponBondCoupons(
                final Period frequency,
                final Money<C> couponAmount,
                final Predicate<? super FixedCoupon<?>> predicate) {
            super(frequency);
            this.couponAmount = couponAmount;
            this.predicate = predicate;
        }

        @Override
        public C currencyId() {
            return couponAmount.currencyId();
        }

        @SuppressWarnings("unchecked")
        @Override
        public FixedCouponBondCoupons<C> filter(final Predicate<? super FixedCoupon<C>> predicate) {
            return new FixedCouponBondCoupons<>(this.frequency(), couponAmount, this.predicate.and((Predicate) predicate));
        }

        @Override
        public ListIterator<FixedCoupon<C>> iterator() {
            return couponDates.transform(this::coupon).iterator();
        }

        @Override
        public FixedCoupon<C> last() {
            return this.coupon(couponDates.last());
        }

        private FixedCoupon<C> coupon(final LocalDate date) {
            return new FixedCoupon<>(date, couponAmount, couponRate);
        }

    }

}
