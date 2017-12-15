package net.meerkat.objects;

import java.util.Optional;

/**
 * @author ollie
 */
public class Classes {

    public static <T> Optional<T> cast(final Object object, final Class<? extends T> clazz) {
        return object != null && clazz.isAssignableFrom(object.getClass())
                ? Optional.of(clazz.cast(object))
                : Optional.empty();
    }

}
