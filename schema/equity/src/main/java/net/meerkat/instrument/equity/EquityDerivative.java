package net.meerkat.instrument.equity;

import net.meerkat.instrument.Instrument;
import net.meerkat.instrument.InstrumentDefinition;
import net.meerkat.instrument.derivative.Derivative;
import net.meerkat.instrument.equity.future.IndexFuture;
import net.meerkat.instrument.equity.future.StockFuture;
import net.meerkat.instrument.equity.option.StockIndexOption;
import net.meerkat.instrument.equity.option.StockOption;
import net.meerkat.instrument.equity.option.Warrant;
import net.meerkat.instrument.equity.swap.BlendedEquitySwap;
import net.meerkat.instrument.equity.swap.EquityForEquitySwap;
import net.meerkat.instrument.equity.swap.InterestForEquitySwap;

/**
 *
 * @author Ollie
 */
public interface EquityDerivative<E extends Instrument>
        extends Derivative<E>, InstrumentDefinition {

    @Override
    default <R> R handleWith(final InstrumentDefinition.Handler<R> handler) {
        return handler instanceof EquityDerivative.Handler
                ? this.handleWith((EquityDerivative.Handler<R>) handler)
                : handler.handleUnknown(this);
    }

    <R> R handleWith(EquityDerivative.Handler<R> handler);

    interface Handler<R> extends InstrumentDefinition.Handler<R> {

        R handle(StockOption option);

        R handle(Warrant warrant);

        R handle(StockIndexOption option);

        R handle(StockFuture stockFuture);

        R handle(IndexFuture indexFuture);

        R handle(InterestForEquitySwap<?> swap);

        R handle(EquityForEquitySwap<?, ?> swap);

        R handle(BlendedEquitySwap blendedSwap);

    }

}
