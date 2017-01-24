package net.meerkat.instrument;

import javax.annotation.Nonnull;

import net.meerkat.identifier.instrument.HasInstrumentIds;
import net.meerkat.identifier.instrument.InstrumentIds;

/**
 *
 * @author Ollie
 */
public interface HasInstrument extends HasInstrumentIds {

    @Nonnull
    Instrument instrument();

    @Override
    default InstrumentIds instrumentIds() {
        return this.instrument().instrumentIds();
    }

}
