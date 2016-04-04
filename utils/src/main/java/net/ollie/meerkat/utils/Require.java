package net.ollie.meerkat.utils;

import java.util.Objects;
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

    public static boolean isTrue(final boolean b, final Supplier<String> message) {
        if (!b) {
            throw new IllegalArgumentException(message.get());
        }
        return b;
    }

    public static void argumentsEqual(final Object left, final Object right) {
        argumentsEqual(left, right, () -> "Objects were not equal: [" + left + "} != [" + right + "]");
    }

    public static void argumentsEqual(final Object left, final Object right, final Supplier<String> message) {
        isTrue(Objects.equals(left, right), message);
    }

}
