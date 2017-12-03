package net.ollie.goat.consumers;

import java.util.function.Consumer;

/**
 * @author Ollie
 */
public final class Consumers {

    private Consumers() {
    }

    public static <T> Consumer<T> ignore() {
        return object -> {
        };
    }

    public static <T> void ifNonNull(final T element, final Consumer<? super T> consumer) {
        if (element != null) {
            consumer.accept(element);
        }
    }

}
