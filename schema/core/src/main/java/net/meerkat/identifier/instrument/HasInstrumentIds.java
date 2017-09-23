package net.meerkat.identifier.instrument;

import java.util.Optional;

import javax.annotation.Nonnull;

import net.coljate.set.Set;

/**
 *
 * @author Ollie
 */
public interface HasInstrumentIds {

    @Nonnull
    InstrumentIds instrumentIds();

    @Nonnull
    default <T extends InstrumentId> Optional<T> instrumentId(final Class<? extends T> clazz) {
        return this.instrumentIds().thatIs(clazz);
    }

    @Nonnull
    default <T extends InstrumentId> Set<T> instrumentIds(final Class<? extends T> clazz) {
        return this.instrumentIds().thatAre(clazz);
    }

}
