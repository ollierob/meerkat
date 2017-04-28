package net.meerkat.utils.collections.sequence;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface Sequence<T> {

    @Nonnull
    Optional<Long> count();

    @Nonnull
    default boolean isFinite() {
        return !this.count().isPresent();
    }

    default boolean isEmpty() {
        return this.count().map(i -> i == 0).orElse(false);
    }

    @Nonnull
    default Sequence<T> where(final Predicate<? super T> predicate) {
        throw new UnsupportedOperationException(); //TODO
    }

    default <R> Sequence<R> transform(Function<? super T, ? extends R> function) {
        throw new UnsupportedOperationException(); //TODO
    }

    static <T> FiniteSequence<T> of(final List<T> list) {
        return FiniteSequence.of(list);
    }

}
