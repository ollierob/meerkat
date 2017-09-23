package net.meerkat.instrument.bond;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyIds;
import net.meerkat.instrument.Callable;
import net.meerkat.instrument.InstrumentDefinition;
import net.meerkat.instrument.Security;
import net.meerkat.instrument.bond.call.BondCall;
import net.meerkat.instrument.bond.coupon.BondCoupons;
import net.meerkat.instrument.bond.dates.BondDates;
import net.meerkat.money.Money;

/**
 *
 * @author Ollie
 */
public interface Bond extends Security, InstrumentDefinition, Callable<BondCall> {

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
        return !this.dates().possibleMaturityDate().isPresent()
                && this.coupons().isFinite();
    }

    @Override
    default CurrencyIds currencyIds() {
        return this.par().currencyIds().union(this.coupons().currencyIds());
    }

    @Override
    default ExplanationBuilder explain() {
        return this.explanationBuilder()
                .put("par", this.par())
                .put("coupons", this.coupons())
                .put("dates", this.dates())
                .put("call", this.call().orElse(null));
    }

    @Override
    @Deprecated
    default Bond instrument() {
        return this;
    }

    @Override
    @Deprecated
    default <R> R handleWith(final InstrumentDefinition.Handler<R> handler) {
        return handler instanceof Bond.Handler
                ? this.handleWith((Bond.Handler<R>) handler)
                : handler.handle(this);
    }

    <R> R handleWith(Bond.Handler<R> handler);

    interface Handler<R> extends InstrumentDefinition.Handler<R> {

        R handle(FixedCouponBond bond);

        R handle(FloatingRateNote bond);

        R handle(PerpetualBond<?> bond);

        R handle(ConvertibleBond bond);

        R handle(VariableRateBond bond);

        @Override
        @Deprecated
        default R handle(final InstrumentDefinition security) {
            return InstrumentDefinition.Handler.super.handle(security);
        }

    }

}
