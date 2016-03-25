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

    public static void argumentsEqual(final Object left, final Object right, final Supplier<String> message) {
        if (!Objects.equals(left, right)) {
            throw new IllegalArgumentException(message.get());
        }
    }

}
