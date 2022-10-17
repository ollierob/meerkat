package net.meerkat.instrument.equity;

import net.meerkat.identifier.instrument.Isin;
import net.meerkat.instrument.InstrumentDefinition;
import net.meerkat.instrument.Security;
import net.meerkat.objects.Castable;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 * @author Ollie
 * @see Stock
 * @see StockIndex
 * @see DepositoryReceipt
 */
public interface Equity
        extends InstrumentDefinition, Security, Castable<Equity> {

    @Nonnull
    default Optional<Isin> isin() {
        return this.instrumentId(Isin.class);
    }

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

        R handle(Stock stock);

        R handle(DepositoryReceipt depositoryReceipt);

    }

}
