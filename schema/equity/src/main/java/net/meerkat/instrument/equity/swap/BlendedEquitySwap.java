package net.meerkat.instrument.equity.swap;

import net.coljate.set.Set;
import net.meerkat.instrument.AggregateInstrument;
import net.meerkat.instrument.derivative.swap.BlendedSwap;
import net.meerkat.instrument.equity.EquityDerivative;

/**
 *
 * @author ollie
 */
public interface BlendedEquitySwap
        extends BlendedSwap, EquityDerivative<AggregateInstrument> {

    @Override
    Set<? extends EquitySwap<?>> swaps();

    @Override
    default <R> R handleWith(final EquityDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

}
