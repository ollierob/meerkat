package net.meerkat.utils;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 *
 * @author Ollie
 */
public class Require {

    protected Require() {
    }

    public static void positive(final int value) {
        positive(value, () -> "Value was not positive: " + value);
    }

    public static void positive(final int value, final Supplier<String> message) {
        if (value <= 0) {
            throw new IllegalArgumentException(message.get());
        }
    }

    public static boolean not(final boolean b, final Supplier<String> message) {
        return argument(!b, message);
    }

    public static boolean not(final boolean b, final String message) {
        return Require.argument(!b, message);
    }

    public static boolean argument(final boolean b, final String message) {
        if (!b) {
            throw new IllegalArgumentException(message);
        }
        return b;
    }

    public static boolean argument(final boolean b, final Supplier<String> message) {
        if (!b) {
            throw new IllegalArgumentException(message.get());
        }
        return b;
    }

    public static void argumentsEqual(final Object left, final Object right) {
        argumentsEqual(left, right, () -> "Objects were not equal: [" + left + "} != [" + right + "]");
    }

    public static void argumentsEqual(final Object left, final Object right, final Supplier<String> message) {
        argument(Objects.equals(left, right), message);
    }

    public static void argumentsNotEqual(final Object left, final Object right, final BiFunction<Object, Object, String> message) {
        if (Objects.equals(left, right)) {
            throw new IllegalArgumentException(message.apply(left, right));
        }
    }

    public static <X extends Exception> boolean that(final boolean b, final Supplier<? extends X> exceptionSupplier) throws X {
        if (!b) {
            throw exceptionSupplier.get();
        }
        return b;
    }

    public static <T, X extends Exception> boolean that(final T object, final Predicate<? super T> predicate, final Function<? super T, ? extends X> makeException) throws X {
        if (!predicate.test(object)) {
            throw makeException.apply(object);
        }
        return true;
    }

}
