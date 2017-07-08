package net.meerkat.instrument;

import net.meerkat.identifier.instrument.InstrumentIds;

/**
 *
 * @author Ollie
 * @see net.meerkat.instrument.Security
 */
public interface Instrument extends HasInstrument {

    @Override
    default Instrument instrument() {
        return this;
    }

    @Override
    InstrumentIds instrumentIds();

}
