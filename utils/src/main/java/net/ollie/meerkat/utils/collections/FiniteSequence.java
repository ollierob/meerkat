package net.ollie.meerkat.utils.collections;

import java.util.AbstractList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

import javax.annotation.CheckForNull;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ollie
 */
@XmlTransient
public class FiniteSequence<T> extends AbstractList<T> implements Sequence<T> {

    private final List<T> delegate;

    public FiniteSequence(final Stream<T> delegate) {
        this(delegate.collect(toList()));
    }

    public FiniteSequence(final List<T> delegate) {
        this.delegate = delegate;
    }

    @Override
    @CheckForNull
    public T first() {
        return delegate.isEmpty() ? null : delegate.get(0);
    }

    @CheckForNull
    public T last() {
        return delegate.isEmpty() ? null : delegate.get(delegate.size() - 1);
    }

    @Override
    public T get(final int index) {
        return delegate.get(index);
    }

    @Override
    public T set(int index, T element) {
        return delegate.set(index, element);
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public Optional<Integer> count() {
        return Optional.of(this.size());
    }

    @Override
    public boolean isFinite() {
        return true;
    }

    @Override
    public FiniteSequence<T> where(final Predicate<? super T> predicate) {
        return new FiniteSequence<>(delegate.stream().filter(predicate));
    }

    @Override
    public <R> FiniteSequence<R> transform(final Function<? super T, ? extends R> function) {
        return Sequence.transformed(delegate, function);
    }

}
