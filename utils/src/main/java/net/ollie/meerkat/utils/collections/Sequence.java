package net.ollie.meerkat.utils.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface Sequence<T> extends Iterable<T> {

    @Nonnull
    Optional<Integer> count();

    @Nonnull
    default boolean isFinite() {
        return !this.count().isPresent();
    }

    default boolean isEmpty() {
        return this.count().map(i -> i == 0).orElse(false);
    }

    default void until(final Predicate<? super T> predicate, final Consumer<? super T> consumer) {
        for (final T next : this) {
            if (predicate.test(next)) {
                consumer.accept(next);
            } else {
                return;
            }
        }
    }

    @Nonnull
    Sequence<T> where(Predicate<? super T> predicate);

    <R> Sequence<R> transform(Function<? super T, ? extends R> function);

    @CheckForNull
    default T first() {
        final Iterator<T> iterator = this.iterator();
        return iterator.hasNext() ? iterator.next() : null;
    }

    static <T> FiniteSequence<T> of(final List<T> list) {
        return new FiniteSequence<>(list);
    }

    @SafeVarargs
    static <T> FiniteSequence<T> of(final T... array) {
        return new FiniteSequence<>(Arrays.asList(array));
    }

    static <F, T> FiniteSequence<T> transformed(final Collection<F> list, final Function<? super F, ? extends T> transform) {
        return of(Lists.eagerlyTransform(list, transform));
    }

}
