package net.meerkat.instrument.equity;

import net.meerkat.instrument.InstrumentDefinition;
import net.meerkat.instrument.Security;
import net.meerkat.instrument.equity.index.StockIndex;

/**
 *
 * @author Ollie
 */
public interface Equity extends InstrumentDefinition, Security {

    @Override
    @Deprecated
    default <R> R handleWith(final InstrumentDefinition.Handler<R> handler) {
        return handler instanceof Equity.Handler
                ? this.handleWith((Equity.Handler<R>) handler)
                : handler.handleUnknown(this);
    }

    <R> R handleWith(Equity.Handler<R> handler);

    interface Handler<R> extends InstrumentDefinition.Handler<R> {

        R handle(StockIndex index);

        R handle(CommonStock stock);

        R handle(PreferredStock stock);

    }

}
