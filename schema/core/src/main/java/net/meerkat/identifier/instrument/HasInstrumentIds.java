package net.meerkat.identifier.instrument;

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

}
