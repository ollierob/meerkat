package net.meerkat.instrument.fx;

import net.meerkat.instrument.InstrumentDefinition;
import net.meerkat.instrument.fx.option.FxOption;
import net.meerkat.instrument.fx.swap.MaturingFxSwap;
import net.meerkat.instrument.fx.swap.PerpetualFxSwap;

/**
 * @author Ollie
 */
public interface FxDerivative extends InstrumentDefinition {

    @Override
    default <R> R handleWith(final InstrumentDefinition.Handler<R> handler) {
        return handler instanceof FxDerivative.Handler
                ? this.handleWith((FxDerivative.Handler<R>) handler)
                : handler.handleUnknown(this);
    }

    <R> R handleWith(FxDerivative.Handler<R> handler);

    interface Handler<R> extends InstrumentDefinition.Handler<R> {

        R handle(MaturingFxSwap<?, ?> swap);

        R handle(PerpetualFxSwap<?, ?> swap);

        R handle(FxOption option);

    }

}
