package net.ollie.meerkat.utils.collections;

import java.util.Iterator;
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
    default Sequence<T> where(Predicate<? super T> predicate) {
        throw new UnsupportedOperationException(); //TODO
    }

    default <R> Sequence<R> transform(Function<? super T, ? extends R> function) {
        throw new UnsupportedOperationException(); //TODO
    }

    @CheckForNull
    default T first() {
        final Iterator<T> iterator = this.iterator();
        return iterator.hasNext() ? iterator.next() : null;
    }

}
