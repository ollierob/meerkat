package net.meerkat.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.RandomAccess;
import java.util.function.Function;
import java.util.function.Predicate;

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

    public static boolean isEmpty(final Iterable<?> iterable) {
        return iterable instanceof Collection
                ? Collections.isEmpty((Collection<?>) iterable)
                : iterable == null || !iterable.iterator().hasNext();
    }

    public static <T> boolean anyIterated(final Iterable<T> iterable, final Predicate<? super T> predicate) {
        for (final var element : iterable) {
            if (predicate.test(element)) return true;
        }
        return false;
    }

    public static <T> boolean allIterated(final Iterable<T> iterable, final Predicate<? super T> predicate) {
        for (final var element : iterable) {
            if (!predicate.test(element)) return false;
        }
        return true;
    }

    public static <T> Optional<T> findFirst(final Iterable<? extends T> iterable, final Predicate<? super T> predicate) {
        for (final var element : iterable) {
            if (predicate.test(element)) return Optional.of(element);
        }
        return Optional.empty();
    }

    public static <T> boolean any(final Iterable<? extends T> iterable, final Predicate<? super T> predicate) {
        if (iterable instanceof Collection) return Collections.any((Collection<T>) iterable, predicate);
        return anyIterated(iterable, predicate);
    }

}
