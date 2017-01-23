package net.meerkat.utils.collections.sequence;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;

import javax.xml.bind.annotation.XmlTransient;

import net.ollie.goat.collection.list.Lists;

/**
 *
 * @author ollie
 */
@XmlTransient
public interface FiniteSequence<T>
        extends StartingSequence<T>, List<T> {

    @Override
    default Optional<Long> count() {
        return Optional.of((long) this.size());
    }

    @Override
    default boolean isFinite() {
        return true;
    }

    @Override
    default boolean isEmpty() {
        return this.count().map(i -> i.intValue() == 0).orElse(false);
    }

    @Override
    default T first() {
        final Iterator<T> iterator = this.iterator();
        return iterator.hasNext() ? iterator.next() : null;
    }

    @Override
    default <R> FiniteSequence<R> transform(Function<? super T, ? extends R> function) {
        return transformed(this, function);
    }

    @Override
    default FiniteSequence<T> where(final Predicate<? super T> predicate) {
        return of(Lists.eagerlyFilter(this, predicate));
    }

    static <T> FiniteSequence<T> of(final List<T> list) {
        return new DelegatedFiniteSequence<>(list);
    }

    @SafeVarargs
    public static <T> FiniteSequence<T> of(final T... array) {
        return new DelegatedFiniteSequence<>(Arrays.asList(array));
    }

    public static <T> FiniteSequence<T> lazy(final int size, final IntFunction<? extends T> create) {
        return of(Lists.lazilyComputed(size, create));
    }

    public static <F, T> FiniteSequence<T> transformed(final Collection<F> list, final Function<? super F, ? extends T> transform) {
        return of(Lists.eagerlyTransform(list, transform));
    }

    static final class DelegatedFiniteSequence<T>
            extends AbstractList<T>
            implements FiniteSequence<T> {

        private final List<T> delegate;

        DelegatedFiniteSequence(final List<T> delegate) {
            this.delegate = delegate;
        }

        @Override
        public T first() {
            return this.isEmpty()
                    ? null
                    : this.get(0);
        }

        @Override
        public T get(final int index) {
            return delegate.get(index);
        }

        @Override
        public int size() {
            return delegate.size();
        }

    }

}
