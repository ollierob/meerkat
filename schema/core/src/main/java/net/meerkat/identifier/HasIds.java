package net.meerkat.identifier;

import java.util.Optional;
import java.util.function.Consumer;

import javax.annotation.Nonnull;

import net.coljate.set.ImmutableSet;
import net.coljate.set.Set;
import net.meerkat.utils.Classes.Castable;
import net.ollie.goat.collectors.OptionalCollectors;

/**
 *
 * @author ollie
 */
public abstract class HasIds<T extends Castable> {

    private final ImmutableSet<? extends T> ids;

    protected HasIds(final Set<? extends T> ids) {
        this.ids = ids.immutableCopy();
    }

    public boolean contains(final T id) {
        return ids.contains(id);
    }

    @Nonnull
    @SuppressWarnings("unchecked")
    public <M extends T> Optional<M> thatIs(final Class<? extends M> clazz) {
        return ids.transform(id -> id.cast(clazz))
                .transform(o -> (Optional<M>) o)
                .collect(OptionalCollectors.oneOrEmpty());
    }

    @Nonnull
    public <M extends T> Set<M> thatAre(final Class<? extends M> clazz) {
        return ids.transform(id -> id.cast(clazz))
                .filter(Optional::isPresent)
                .transform(o -> (M) o.get())
                .collect(Set.collector());
    }

    public boolean isEmpty() {
        return ids.isEmpty();
    }

    public int count() {
        return ids.count();
    }

    @Nonnull
    public void accept(final Consumer<? super T> consumer) {
        ids.forEach(consumer);
    }

}
