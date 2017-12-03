package net.meerkat.functions.exceptions;

import java.util.function.Supplier;

/**
 * @author Ollie
 */
public final class Exceptions {

    private Exceptions() {
    }

    public static <T, X extends RuntimeException> T throwRuntime(final Supplier<X> exception) {
        throw exception.get();
    }

    public static <T> T throwIllegalArgumentException(final String message) {
        throw new IllegalArgumentException(message);
    }

}
