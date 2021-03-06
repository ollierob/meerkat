package net.meerkat.instrument.bond;

import net.meerkat.identifier.currency.CurrencyIds;
import net.meerkat.identifier.currency.HasCurrencyIds;
import net.meerkat.identifier.instrument.Isin;
import net.meerkat.instrument.Callable;
import net.meerkat.instrument.InstrumentDefinition;
import net.meerkat.instrument.Security;
import net.meerkat.instrument.bond.call.BondCall;
import net.meerkat.instrument.bond.coupon.BondCoupons;
import net.meerkat.instrument.bond.dates.BondDates;
import net.meerkat.money.Money;
import net.meerkat.objects.Castable;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 * @author Ollie
 */
public interface Bond
        extends Security, InstrumentDefinition, HasCurrencyIds, Callable<BondCall>, Castable<Bond> {

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

    @Nonnull
    default Optional<Isin> isin() {
        return this.instrumentId(Isin.class);
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
                : handler.handleUnknown(this);
    }

    <R> R handleWith(Bond.Handler<R> handler);

    interface Handler<R> extends InstrumentDefinition.Handler<R> {

        R handle(FixedCouponBond bond);

        R handle(FloatingRateNote bond);

        R handle(PerpetualBond<?> bond);

        R handle(ConvertibleBond bond);

        R handle(VariableRateBond bond);

    }

}
