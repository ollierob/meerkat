package net.meerkat.functions.consumers;

import javax.annotation.Nullable;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author Ollie
 * @see Consumer
 */
public interface CheckedConsumer<T, X extends Exception> {

    void accept(@Nullable T t) throws X;

    default CheckedConsumer<T, X> andThen(final Consumer<? super T> after) {
        return element -> {
            this.accept(element);
            after.accept(element);
        };
    }

    default Consumer<T> unchecked() {
        return this.unchecked(RuntimeException::new);
    }

    default Consumer<T> unchecked(final Function<? super X, ? extends RuntimeException> toException) {
        return element -> {
            try {
                this.accept(element);
            } catch (final RuntimeException rex) {
                throw rex;
            } catch (final Exception ex) {
                throw toException.apply((X) ex);
            }
        };
    }

}
