package net.meerkat.collections;

import java.util.Collection;
import java.util.function.IntFunction;
import java.util.function.Predicate;

/**
 * @author Ollie
 */
public abstract class Collections {

    protected Collections() {
    }

    public static <T> boolean any(final Iterable<T> iterable, final Predicate<? super T> predicate) {
        for (final T element : iterable) {
            if (predicate.test(element)) {
                return true;
            }
        }
        return false;
    }

    public static <T> T[] toArray(final Collection<T> collection, final IntFunction<T[]> arrayCreator) {
        return collection.toArray(arrayCreator.apply(collection.size()));
    }

}
