package net.ollie.goat.optionals;

import net.ollie.goat.exceptions.Exceptions;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

/**
 * @author Ollie
 */
public final class Optionals {

    private Optionals() {
    }

    public static boolean equalIfPresent(final Optional<?> left, final Optional<?> right) {
        return Optionals.equalIfPresent(left, right, Objects::equals);
    }

    public static <T> boolean equalIfPresent(final Optional<? extends T> left, final Optional<? extends T> right, final BiPredicate<? super T, ? super T> predicate) {
        return left.isPresent() && right.isPresent()
                ? predicate.test(left.get(), right.get())
                : false;
    }

    public static boolean equalIfAbsent(final Optional<?> left, final Optional<?> right) {
        return equalIfAbsent(left, right, Objects::equals);
    }

    public static <T> boolean equalIfAbsent(final Optional<? extends T> left, final Optional<? extends T> right, final BiPredicate<? super T, ? super T> predicate) {
        return left.isPresent() && right.isPresent()
                ? predicate.test(left.get(), right.get())
                : true;
    }

    public static <T> Optional<T> firstPresent(final Optional<T> optional, final Supplier<Optional<T>> supplier) {
        return optional.isPresent()
                ? optional
                : supplier.get();
    }

    @Nonnull
    public static <T> Optional<T> zeroOrOne(final Iterable<T> iterable) {
        return zeroOrOne(iterable, () -> null);
    }

    @Nonnull
    public static <T> Optional<T> zeroOrOne(final Iterable<T> iterable, @Nonnull final Supplier<T> defaultValue) {
        final Iterator<T> iterator = iterable.iterator();
        if (!iterator.hasNext()) {
            return Optional.ofNullable(defaultValue.get());
        }
        iterator.next();
        return iterator.hasNext()
                ? Exceptions.throwIllegalArgumentException("More than one element inside [" + iterable + "]!")
                : Optional.empty();
    }

}
