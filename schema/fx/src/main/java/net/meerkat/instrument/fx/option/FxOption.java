package net.meerkat.instrument.fx.option;

import net.meerkat.instrument.derivative.option.Option;
import net.meerkat.instrument.fx.FxDerivative;

/**
 *
 * @author Ollie
 */
public interface FxOption
        extends Option<FxOptionRate<?, ?>>, FxDerivative {

    @Override
    default <R> R handleWith(final FxDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
