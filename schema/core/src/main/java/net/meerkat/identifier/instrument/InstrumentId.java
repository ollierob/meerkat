package net.meerkat.identifier.instrument;

import net.ollie.meerkat.utils.Classes.Castable;

/**
 *
 * @author Ollie
 */
public interface InstrumentId extends HasInstrumentId, Castable<InstrumentId> {

    @Override
    @Deprecated
    default InstrumentId instrumentId() {
        return this;
    }

}
