package net.meerkat.identifier.instrument;

import net.meerkat.objects.Castable;

/**
 * Instrument identifier.
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
