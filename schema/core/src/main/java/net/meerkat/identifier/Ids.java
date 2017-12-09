package net.meerkat.identifier;

import net.coljate.set.Set;
import net.meerkat.functions.collectors.OptionalCollectors;
import net.meerkat.objects.Classes.Castable;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 *
 * @author ollie
 */
public interface Ids<T extends Castable<T>> {

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
