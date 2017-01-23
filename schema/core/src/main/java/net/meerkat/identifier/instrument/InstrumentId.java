package net.meerkat.identifier.instrument;

import net.meerkat.utils.Classes.Castable;

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
