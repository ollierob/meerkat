package net.meerkat.instrument.fx.swap;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.dates.Matures;
import net.meerkat.instrument.derivative.swap.Swap;
import net.meerkat.instrument.derivative.swap.SwapLegs;
import net.meerkat.instrument.fx.FxDerivative;

/**
 *
 * @author ollie
 */
public interface MaturingFxSwap<P extends CurrencyId, R extends CurrencyId>
        extends Swap, FxDerivative, Matures {

    @Nonnull
    FxSwapLeg<P, R> nearLeg();

    @Nonnull
    FxSwapLeg<R, P> farLeg();

    @Override
    default LocalDate maturityDate() {
        return this.farLeg().deliveryDate();
    }

    @Override
    default SwapLegs.Finite<FxSwapLeg<?, ?>> legs() {
        return SwapLegs.of(this.nearLeg(), this.farLeg());
    }

    @Override
    default <R> R handleWith(final FxDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
