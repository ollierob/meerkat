package net.meerkat.objects;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author Ollie
 */
public class Arguments {

    protected Arguments() {
    }

    public static void requirePositive(final int value) {
        requirePositive(value, () -> "Value was not positive: " + value);
    }

    public static void requirePositive(final int value, final Supplier<String> message) {
        if (value <= 0) {
            throw new IllegalArgumentException(message.get());
        }
    }

    public static boolean requireNot(final boolean b, final Supplier<String> message) {
        return require(!b, message);
    }

    public static boolean requireNot(final boolean b, final String message) {
        return Arguments.require(!b, message);
    }

    public static boolean require(final boolean b, final String message) {
        if (!b) {
            throw new IllegalArgumentException(message);
        }
        return b;
    }

    public static boolean require(final boolean b, final Supplier<String> message) {
        if (!b) {
            throw new IllegalArgumentException(message.get());
        }
        return b;
    }

    public static void requireEqual(final Object left, final Object right) {
        requireEqual(left, right, () -> "Objects were not equal: [" + left + "} != [" + right + "]");
    }

    public static void requireEqual(final Object left, final Object right, final Supplier<String> message) {
        require(Objects.equals(left, right), message);
    }

    public static void requireNotEqual(final Object left, final Object right, final BiFunction<Object, Object, String> message) {
        if (Objects.equals(left, right)) {
            throw new IllegalArgumentException(message.apply(left, right));
        }
    }

    public static <X extends Exception> boolean requireOrThrow(final boolean b, final Supplier<? extends X> exceptionSupplier) throws X {
        if (!b) {
            throw exceptionSupplier.get();
        }
        return b;
    }

    public static <T, X extends Exception> boolean requireOrThrow(final T object, final Predicate<? super T> predicate, final Function<? super T, ? extends X> makeException) throws X {
        if (!predicate.test(object)) {
            throw makeException.apply(object);
        }
        return true;
    }

    public static <T> boolean require(final T object, final Predicate<? super T> predicate, final Function<? super T, String> errorMessage) {
        if (!predicate.test(object)) {
            throw new IllegalArgumentException(errorMessage.apply(object));
        }
        return true;
    }

}
