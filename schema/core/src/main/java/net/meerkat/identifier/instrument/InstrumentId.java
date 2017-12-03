package net.meerkat.identifier.instrument;

import net.meerkat.objects.Classes.Castable;

/**
 *
 * @author Ollie
 */
public interface InstrumentId extends HasInstrumentId, Castable {

    @Override
    @Deprecated
    default InstrumentId instrumentId() {
        return this;
    }

}
