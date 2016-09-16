package net.ollie.meerkat.security.bond;

import javax.annotation.Nonnull;

import net.ollie.goat.money.Money;
import net.ollie.meerkat.security.Callable;
import net.ollie.meerkat.security.SecurityDefinition;
import net.ollie.meerkat.security.bond.call.BondCall;
import net.ollie.meerkat.security.bond.coupon.BondCoupons;
import net.ollie.meerkat.security.bond.dates.BondDates;

/**
 *
 * @author Ollie
 */
public interface Bond extends SecurityDefinition, Callable<BondCall> {

    @Nonnull
    Money<?> par();

    @Nonnull
    BondCoupons<?> coupons();

    @Nonnull
    BondDates dates();

    default boolean isZeroCoupon() {
        return this.coupons().isEmpty();
    }

    default boolean isAmortizing() {
        return !this.dates().maturity().isPresent()
                && this.coupons().count().isPresent();
    }

    @Override
    default ExplanationBuilder explain() {
        return new ExplanationBuilder()
                .put("par", this.par())
                .put("coupons", this.coupons())
                .put("dates", this.dates())
                .put("call", this.call());
    }

    @Override
    @Deprecated
    default <R> R handleWith(final SecurityDefinition.Handler<R> handler) {
        return handler instanceof Bond.Handler
                ? this.handleWith((Bond.Handler<R>) handler)
                : handler.handle(this);
    }

    <R> R handleWith(Bond.Handler<R> handler);

    interface Handler<R> extends SecurityDefinition.Handler<R> {

        R handle(FixedCouponBond bond);

        R handle(FloatingRateNote bond);

        R handle(PerpetualBond bond);

        R handle(ConvertibleBond bond);

        R handle(VariableRateBond bond);

        @Override
        @Deprecated
        default R handle(final SecurityDefinition security) {
            return SecurityDefinition.Handler.super.handle(security);
        }

    }

}
