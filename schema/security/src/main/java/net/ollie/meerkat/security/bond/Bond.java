package net.ollie.meerkat.security.bond;

import javax.annotation.Nonnull;

import net.ollie.meerkat.security.Callable;
import net.ollie.meerkat.security.Security;
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
    BondCoupons<?> coupons();

    @Nonnull
    BondNominal nominal();

    @Nonnull
    BondDates dates();

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

        @Override
        @Deprecated
        default R handle(final Security security) {
            return SecurityDefinition.Handler.super.handle(security);
        }

    }

}
