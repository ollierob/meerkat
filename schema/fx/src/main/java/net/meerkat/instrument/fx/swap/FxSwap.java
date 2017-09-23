package net.meerkat.instrument.fx.swap;

import javax.annotation.Nonnull;

import net.coljate.list.List;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.derivative.swap.Swap;
import net.meerkat.instrument.fx.FxDerivative;

/**
 *
 * @author ollie
 */
public interface FxSwap<P extends CurrencyId, R extends CurrencyId>
        extends Swap, FxDerivative {

    @Nonnull
    FxSwapLeg<P, R> nearLeg();

    @Nonnull
    FxSwapLeg<R, P> farLeg();

    @Override
    default List<FxSwapLeg<?, ?>> legs() {
        return List.of(this.nearLeg(), this.farLeg());
    }

    @Override
    default <R> R handleWith(final FxDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
