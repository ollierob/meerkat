package net.meerkat.identifier;

import java.util.Optional;
import java.util.function.Consumer;

import javax.annotation.Nonnull;

import net.coljate.set.ImmutableSet;
import net.coljate.set.Set;
import net.meerkat.utils.Classes.Castable;

/**
 *
 * @author ollie
 */
public abstract class HasIds<T extends Castable> {

    private final ImmutableSet<T> ids;

    protected HasIds(final Set<T> ids) {
        this.ids = ids.immutableCopy();
    }

    public boolean contains(final T id) {
        return ids.contains(id);
    }

    @Nonnull
    public <M extends T> Optional<M> id(final Class<M> clazz) {
        return ids.stream()
                .map(id -> id.cast(clazz))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findAny();
    }

    public boolean isEmpty() {
        return ids.isEmpty();
    }

    public int size() {
        return ids.count();
    }

    @Nonnull
    public void accept(final Consumer<? super T> consumer) {
        ids.forEach(consumer);
    }

}
