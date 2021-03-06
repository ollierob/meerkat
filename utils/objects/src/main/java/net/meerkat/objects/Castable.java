package net.meerkat.objects;

import net.meerkat.collections.list.Lists;

import java.util.Optional;
import java.util.Set;

public interface Castable<T> {

    static <T> Optional<T> cast(final Object object, final Class<? extends T> clazz) {
        return clazz.isAssignableFrom(object.getClass())
                ? Optional.of(clazz.cast(object))
                : Optional.empty();
    }

    default <R extends T> Optional<R> as(final Class<? extends R> clazz) {
        return cast(this, clazz);
    }

    default boolean is(final Class<?> type) {
        return type.isAssignableFrom(this.getClass());
    }

    default boolean isAny(final Set<? extends Class<?>> types) {
        return Lists.any(types, this::is);
    }

}
