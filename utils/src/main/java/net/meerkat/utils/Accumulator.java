package net.meerkat.utils;

import java.util.stream.Collector;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 * Stateless value accumulator.
 *
 * Can be converted into a stateful {@link #collector Collector}.
 *
 * @author Ollie
 * @param <R> collector type (e.g. a list)
 * @param <T> value type
 */
public interface Accumulator<R, T> {

    R container();

    R fold(R container, T value);

    R combine(@CheckForNull R left, @Nonnull R right);

    T finish(R container);

    @Nonnull
    default Collector<T, R, T> collector() {
        return Collector.of(
                this::container,
                this::fold,
                this::combine,
                this::finish);
    }

    @Deprecated
    default T accumulate(final T value) {
        return value;
    }

    default T accumulate(final T left, final T right) {
        final R container = this.container();
        this.fold(container, left);
        this.fold(container, right);
        return this.finish(container);
    }

    default T accumulate(final Iterable<T> values) {
        final R container = this.container();
        values.forEach(value -> this.fold(container, value));
        return this.finish(container);
    }

    interface Homogeneous<T> extends Accumulator<T, T> {

        @Override
        default T container() {
            return null;
        }

        @Override
        default T fold(final T container, final T value) {
            return this.combine(container, value);
        }

        @Override
        default T accumulate(final T left, final T right) {
            return this.combine(left, right);
        }

        @Override
        public default T finish(final T container) {
            return container;
        }

    }

}
