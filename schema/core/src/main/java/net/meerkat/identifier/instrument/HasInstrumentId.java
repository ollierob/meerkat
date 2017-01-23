package net.meerkat.identifier.instrument;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface HasInstrumentId extends HasInstrumentIds {

    @Nonnull
    InstrumentId instrumentId();

    @Override
    default InstrumentIds instrumentIds() {
        return InstrumentIds.of(this.instrumentId());
    }

}
