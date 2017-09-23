package net.meerkat.identifier;

import java.util.Optional;

import javax.annotation.Nonnull;

import net.coljate.set.Set;
import net.meerkat.utils.Classes.Castable;
import net.ollie.goat.collectors.OptionalCollectors;

/**
 *
 * @author ollie
 */
public interface Ids<T extends Castable> {

    @Nonnull
    Set<? extends T> values();

    default int count() {
        return this.values().count();
    }

    default boolean isEmpty() {
        return this.values().isEmpty();
    }

    default <R extends T> Set<R> thatAre(final Class<? extends R> clazz) {
        return this.values()
                .transform(id -> id.<R>cast(clazz))
                .filter(Optional::isPresent)
                .transform(Optional::get)
                .collect(Set.collector());
    }

    default <R extends T> Optional<R> thatIs(final Class<? extends R> clazz) {
        return this.values()
                .transform(id -> id.<R>cast(clazz))
                .collect(OptionalCollectors.oneOrEmpty());
    }

}
