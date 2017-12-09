package net.meerkat.identifier;

import net.coljate.set.ImmutableSet;
import net.coljate.set.Set;
import net.meerkat.objects.Classes.Castable;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

/**
 *
 * @author ollie
 */
public abstract class IdSet<T extends Castable<T>> implements Ids<T> {

    private final ImmutableSet<? extends T> ids;

    protected IdSet(final Set<? extends T> ids) {
        this.ids = ids.immutableCopy();
    }

    @Override
    public Set<? extends T> values() {
        return ids;
    }

    public boolean contains(final T id) {
        return ids.contains(id);
    }

    @Override
    public boolean isEmpty() {
        return ids.isEmpty();
    }

    @Override
    public int count() {
        return ids.count();
    }

    @Nonnull
    public void accept(final Consumer<? super T> consumer) {
        ids.forEach(consumer);
    }

}
