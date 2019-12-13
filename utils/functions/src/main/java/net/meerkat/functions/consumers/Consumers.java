package net.meerkat.functions.consumers;

import java.util.function.Consumer;

/**
 * @author Ollie
 */
public final class Consumers {

    private Consumers() {
    }

    public static <T> void ifNonNull(final T object, final Consumer<? super T> ifNonNull) {
        if (object != null) ifNonNull.accept(object);
    }

}
