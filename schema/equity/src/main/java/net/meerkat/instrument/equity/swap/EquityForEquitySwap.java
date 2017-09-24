package net.meerkat.instrument.equity.swap;

import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.Instrument;
import net.meerkat.instrument.equity.EquityDerivative;
import net.meerkat.instrument.equity.swap.EquityForEquitySwap.EquityPair;

/**
 *
 * @author ollie
 */
public interface EquityForEquitySwap
        extends EquitySwap<EquityPair> {

    @Override
    default <R> R handleWith(final EquityDerivative.Handler<R> handler) {
        return handler.handle(this);
    }

    class EquityPair implements Instrument {

        public EquityPair(final InstrumentId first, final InstrumentId second) {
        }

        @Override
        public InstrumentIds instrumentIds() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

}
