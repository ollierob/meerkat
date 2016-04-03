package net.ollie.meerkat.utils.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntFunction;
import static java.util.stream.Collectors.toList;

import javax.annotation.CheckForNull;

/**
 *
 * @author Ollie
 */
public final class Lists extends Collections {

    private Lists() {
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

    @CheckForNull
    public static <T> T first(final List<T> list) {
        return list.isEmpty() ? null : list.get(0);
    }

    @CheckForNull
    public static <T> T last(final List<T> list) {
        return list.isEmpty() ? null : list.get(list.size() - 1);
    }

    public static <T> List<T> lazy(final int size, final IntFunction<? extends T> func) {
        return size == 0
                ? java.util.Collections.emptyList()
                : new LazyList<>(size, func);
    }

}
