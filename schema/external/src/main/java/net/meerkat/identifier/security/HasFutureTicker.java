package net.meerkat.identifier.security;

import java.util.Optional;

import javax.annotation.Nonnull;

import net.meerkat.identifier.instrument.InstrumentId;

/**
 *
 * @author Ollie
 */
public interface HasFutureTicker extends InstrumentId {

    @Nonnull
    default Optional<FutureTicker> futureTicker() {
        return this.instrumentId(FutureTicker.class);
    }

}
