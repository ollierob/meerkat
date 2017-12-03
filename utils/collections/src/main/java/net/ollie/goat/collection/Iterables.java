package net.ollie.goat.collection;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author Ollie
 */
public abstract class Iterables {

    protected Iterables() {
    }

    public static <F, T> T requireCommonElement(final Iterable<F> iterable, final Function<? super F, ? extends T> transform) {
        final Iterator<F> iterator = iterable.iterator();
        if (!iterator.hasNext()) {
            return null;
        }
        final T common = transform.apply(iterator.next());
        while (iterator.hasNext()) {
            final T next = transform.apply(iterator.next());
            if (!Objects.equals(common, next)) {
                throw new IllegalArgumentException(); //TODO
            }
        }
        return common;
    }

    public static <T extends Comparable<? super T>> boolean isIncreasing(final Iterable<T> iterable) {
        T element = null;
        for (final T next : iterable) {
            if (element != null && element.compareTo(next) < 0) {
                return false;
            }
            element = next;
        }
        return true;
    }

    public static <T extends Comparable<? super T>> boolean allEqual(final Iterable<T> iterable) {
        final Iterator<T> iterator = iterable.iterator();
        final T first = iterator.next();
        while (iterator.hasNext()) {
            if (!Objects.equals(first, iterator.next())) {
                return false;
            }
        }
        return true;
    }

}
