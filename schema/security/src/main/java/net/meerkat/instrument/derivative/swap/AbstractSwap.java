package net.meerkat.instrument.derivative.swap;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.NamedInstrument;

/**
 *
 * @author Ollie
 */
public abstract class AbstractSwap extends NamedInstrument implements Swap {

    @Deprecated
    protected AbstractSwap() {
    }

    protected AbstractSwap(final String name, final InstrumentIds identifiers) {
        super(name, identifiers);
    }

}
