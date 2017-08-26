package net.meerkat.instrument.equity;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.NamedInstrument;

/**
 *
 * @author Ollie
 */
public class StockIndex extends NamedInstrument {

    private static final long serialVersionUID = 1L;

    public StockIndex(final String name, final InstrumentIds identifiers) {
        super(name, identifiers);
    }

}
