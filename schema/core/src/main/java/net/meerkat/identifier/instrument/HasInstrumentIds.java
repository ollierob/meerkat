package net.meerkat.identifier.instrument;

import java.util.Optional;
import java.util.function.Consumer;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface HasInstrumentIds {

    @Nonnull
    InstrumentIds instrumentIds();

    @Nonnull
    default void instrumentIds(final Consumer<InstrumentId> consumer) {
        this.instrumentIds().accept(consumer);
    }

    default <R extends InstrumentId> Optional<R> instrumentId(final Class<R> clazz) {
        return this.instrumentIds().id(clazz);
    }

}
