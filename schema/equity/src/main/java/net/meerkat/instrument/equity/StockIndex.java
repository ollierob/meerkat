package net.meerkat.instrument.equity;

/**
 *
 * @author Ollie
 */
public interface StockIndex extends Equity {

    @Override
    default <R> R handleWith(final Equity.Handler<R> handler) {
        return handler.handle(this);
    }

}
