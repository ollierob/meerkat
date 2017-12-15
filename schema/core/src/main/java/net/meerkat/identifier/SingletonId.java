package net.meerkat.identifier;

import net.coljate.set.Set;
import net.meerkat.objects.Classes;

import javax.annotation.Nonnull;

public class SingletonId<T extends Classes.Castable<T>> implements Ids<T> {

    private final T value;

    public SingletonId(final T value) {
        this.value = value;
    }

    @Nonnull
    @Override
    public Set<? extends T> values() {
        return Set.of(value);
    }

}
