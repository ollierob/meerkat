package net.meerkat.instrument.fx;

import net.meerkat.instrument.fx.option.FxOption;
import net.meerkat.instrument.fx.swap.FxSwap;
import net.meerkat.instrument.InstrumentDefinition;

/**
 *
 * @author Ollie
 */
public interface FxDerivative extends InstrumentDefinition {

    @Override
    default <R> R handleWith(final InstrumentDefinition.Handler<R> handler) {
        return handler instanceof FxDerivative.Handler
                ? this.handleWith((FxDerivative.Handler<R>) handler)
                : handler.handle(this);
    }

    <R> R handleWith(FxDerivative.Handler<R> handler);

    interface Handler<R> extends InstrumentDefinition.Handler<R> {

        R handle(FxSwap swap);

        R handle(FxOption option);

    }

}
