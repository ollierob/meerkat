package net.meerkat.instrument.equity.swap;

import javax.annotation.Nonnull;

import net.meerkat.instrument.equity.Equity;
import net.meerkat.instrument.equity.EquityDerivative;

/**
 *
 * @author ollie
 */
public interface EquityForEquitySwap<E extends Equity, F extends Equity>
        extends EquitySwap<E> {

    @Nonnull
    F contra();

    @Override
    default <R> R handleWith(final EquityDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
