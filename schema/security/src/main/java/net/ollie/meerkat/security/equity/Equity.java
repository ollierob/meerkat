package net.ollie.meerkat.security.equity;

import net.ollie.meerkat.security.SecurityDefinition;

/**
 *
 * @author Ollie
 */
public interface Equity extends SecurityDefinition {

    @Override
    @Deprecated
    default <R> R handleWith(final SecurityDefinition.Handler<R> handler) {
        return handler instanceof Equity.Handler
                ? this.handleWith((Equity.Handler<R>) handler)
                : handler.handle(this);
    }

    <R> R handleWith(Equity.Handler<R> handler);

    interface Handler<R> extends SecurityDefinition.Handler<R> {

        R handle(Stock stock);

    }

}
