package net.meerkat.instrument;

import java.security.Security;

import net.meerkat.identifier.instrument.InstrumentIds;

/**
 *
 * @author Ollie
 * @see Security
 */
public interface Instrument extends HasInstrument {

    @Override
    default Instrument instrument() {
        return this;
    }

    @Override
    InstrumentIds instrumentIds();

}
