package net.ollie.meerkat.utils.collections;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntFunction;

import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ollie
 */
@XmlTransient
public abstract class FiniteSequence<T> extends AbstractList<T> implements Sequence<T> {

    @Override
    public Optional<Integer> count() {
        return Optional.of(this.size());
    }

    @Override
    public boolean isFinite() {
        return true;
    }

    public static <T> FiniteSequence<T> of(final List<T> list) {
        return new DelegatedFiniteSequence<>(list);
    }

    @SafeVarargs
    public static <T> FiniteSequence<T> of(final T... array) {
        return new DelegatedFiniteSequence<>(Arrays.asList(array));
    }

    public static <T> FiniteSequence<T> lazy(final int size, final IntFunction<? extends T> create) {
        return of(Lists.lazy(size, create));
    }

    public static <F, T> FiniteSequence<T> transformed(final Collection<F> list, final Function<? super F, ? extends T> transform) {
        return of(Lists.eagerlyTransform(list, transform));
    }

    static final class DelegatedFiniteSequence<T> extends FiniteSequence<T> {

        private final List<T> delegate;

        DelegatedFiniteSequence(final List<T> delegate) {
            this.delegate = delegate;
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
