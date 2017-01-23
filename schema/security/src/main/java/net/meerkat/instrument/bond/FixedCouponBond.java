package net.meerkat.instrument.bond;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.cash.CashPayment;
import net.meerkat.instrument.bond.call.BondCall;
import net.meerkat.instrument.bond.coupon.FixedRateCoupon;
import net.meerkat.instrument.bond.dates.MaturingBondDates;
import net.meerkat.issuer.IssuerId;
import net.meerkat.money.Money;
import net.meerkat.money.interest.fixed.FixedInterestRate;
import net.meerkat.numeric.interest.InterestRateSecurity;
import net.meerkat.money.currency.CurrencyId;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class FixedCouponBond
        extends StraightBond
        implements InterestRateSecurity {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "coupon_rate")
    private FixedInterestRate couponRate;

    @XmlElementRef(name = "coupon_amount")
    private Money<?> couponAmount;

    @XmlElement(name = "coupon_date")
    private List<LocalDate> couponDates;

    @Deprecated
    FixedCouponBond() {
    }

    public FixedCouponBond(
            final String name,
            final InstrumentIds identifiers,
            final Money<?> par,
            final MaturingBondDates dates,
            final Money<?> couponAmount,
            final FixedInterestRate couponRate,
            final List<LocalDate> couponDates,
            final BondCall call,
            final IssuerId issuer) {
        super(name, identifiers, par, dates, call, issuer);
        this.couponRate = couponRate;
        this.couponAmount = couponAmount;
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
        return new FixedCouponBondCoupons<>(couponAmount);
    }

    @Override
    public <R> R handleWith(final Bond.Handler<R> handler) {
        return handler.handle(this);
    }

    public class FixedCouponBondCoupons<C extends CurrencyId> extends StraightBondCoupons<FixedRateCoupon<C>> {

        private final Money<C> couponAmount;

        FixedCouponBondCoupons(final Money<C> couponAmount) {
            this.couponAmount = couponAmount;
        }

        @Override
        public FixedRateCoupon<C> get(final int index) {
            return new FixedRateCoupon<>(couponDates.get(index), couponAmount, couponRate);
        }

        @Override
        public int size() {
            return couponDates.size();
        }

        @Override
        public C currency() {
            return couponAmount.currency();
        }

    }

}
