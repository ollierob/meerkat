package net.meerkat.instrument.equity.future;

import net.meerkat.instrument.equity.EquityDerivative;
import net.meerkat.instrument.equity.Stock;

/**
 * Single stock future.
 *
 * @author ollie
 */
public interface StockFuture extends EquityFuture<Stock> {

    @Override
    default <R> R handleWith(final EquityDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
