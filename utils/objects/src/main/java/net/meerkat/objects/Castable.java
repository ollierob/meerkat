package net.meerkat.objects;

import javax.annotation.Nonnull;
import java.util.Optional;

public interface Castable<T> {

    @Nonnull
    default <R extends T> Optional<R> cast(final Class<? extends R> clazz) {
        return Classes.cast(this, clazz);
    }

}
