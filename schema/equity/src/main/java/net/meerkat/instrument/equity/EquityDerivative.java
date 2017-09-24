package net.meerkat.instrument.equity;

import net.meerkat.instrument.Instrument;
import net.meerkat.instrument.InstrumentDefinition;
import net.meerkat.instrument.derivative.Derivative;
import net.meerkat.instrument.equity.future.IndexFuture;
import net.meerkat.instrument.equity.future.StockFuture;
import net.meerkat.instrument.equity.option.StockIndexOption;
import net.meerkat.instrument.equity.option.Warrant;

/**
 *
 * @author Ollie
 */
public interface EquityDerivative<E extends Instrument> extends Derivative<E>, InstrumentDefinition {

    @Override
    default <R> R handleWith(final InstrumentDefinition.Handler<R> handler) {
        return handler instanceof EquityDerivative.Handler
                ? this.handleWith((EquityDerivative.Handler<R>) handler)
                : handler.handle(this);
    }

    <R> R handleWith(EquityDerivative.Handler<R> handler);

    interface Handler<R> extends InstrumentDefinition.Handler<R> {

        R handle(Warrant warrant);

        R handle(StockIndexOption option);

        R handle(StockFuture stockFuture);

        R handle(IndexFuture indexFuture);

    }

}
