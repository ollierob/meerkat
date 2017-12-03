package net.ollie.goat.collectors;

import net.ollie.goat.exceptions.Exceptions;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.function.Supplier;

/**
 * @author ollie
 */
public class CountingCollectors {

    public static <T> T zeroOrOne(final Iterable<T> iterable) {
        return zeroOrOne(iterable, () -> null);
    }

    public static <T> T zeroOrOne(final Iterable<T> iterable, @Nonnull final Supplier<T> defaultValue) {
        final Iterator<T> iterator = iterable.iterator();
        if (!iterator.hasNext()) {
            return defaultValue.get();
        }
        final T first = iterator.next();
        return iterator.hasNext()
                ? Exceptions.throwIllegalArgumentException("More than one element inside [" + iterable + "]!")
                : first;
    }

}
