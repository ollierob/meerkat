package net.ollie.goat.collection.list;

import net.ollie.goat.collection.Collections;

import javax.annotation.CheckForNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

/**
 * @author Ollie
 */
public abstract class Lists extends Collections {

    protected Lists() {
    }

    public static <T> List<T> copyIntoLeft(final List<T> left, final List<? extends T> right) {
        left.addAll(right);
        return left;
    }

    public static <T> T reduce(final List<T> list, final BinaryOperator<T> operator) {
        final Iterator<T> iterator = list.iterator();
        if (!iterator.hasNext()) {
            return null;
        }
        T current = iterator.next();
        while (iterator.hasNext()) {
            current = operator.apply(current, iterator.next());
        }
        return current;
    }

    public static <F, T> List<T> eagerlyTransform(final Collection<F> collection, final Function<? super F, ? extends T> transform) {
        return collection.stream().map(transform).collect(toList());
    }

    public static <T> List<T> eagerlyFilter(final Iterable<? extends T> list, final Predicate<? super T> predicate) {
        final List<T> out = new ArrayList<>();
        list.forEach(element -> {
            if (predicate.test(element)) {
                out.add(element);
            }
        });
        return out;
    }

    @CheckForNull
    public static <T> T first(final List<T> list) {
        return list.isEmpty() ? null : list.get(0);
    }

    @CheckForNull
    public static <T> T last(final List<T> list) {
        return list.isEmpty() ? null : list.get(list.size() - 1);
    }

    public static <T> List<T> lazilyComputed(final int size, final IntFunction<? extends T> func) {
        return size == 0
                ? java.util.Collections.emptyList()
                : new LazyList<>(size, func);
    }

}
