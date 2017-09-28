package net.meerkat.instrument.equity.future;

import net.meerkat.instrument.equity.EquityDerivative;
import net.meerkat.instrument.equity.index.StockIndex;

/**
 * Stock index future.
 *
 * @author ollie
 */
public interface IndexFuture extends EquityFuture<StockIndex> {

    @Override
    default <R> R handleWith(final EquityDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
