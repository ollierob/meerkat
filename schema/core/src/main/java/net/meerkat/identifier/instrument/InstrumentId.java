package net.meerkat.identifier.instrument;

import net.meerkat.objects.Classes;

/**
 * @author Ollie
 */
public interface InstrumentId extends HasInstrumentId, Classes.Castable<InstrumentId> {

    @Override
    @Deprecated
    default InstrumentId instrumentId() {
        return this;
    }

}
