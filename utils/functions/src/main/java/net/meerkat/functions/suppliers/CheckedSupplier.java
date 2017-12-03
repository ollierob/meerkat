package net.meerkat.functions.suppliers;

import javax.annotation.CheckForNull;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * @author Ollie
 * @see Supplier
 */
public interface CheckedSupplier<T, X extends Exception> {

    @CheckForNull
    T get() throws X;

    @CheckReturnValue
    default <R> CheckedSupplier<R, X> andThen(@Nonnull final Function<? super T, ? extends R> after) {
        requireNonNull(after);
        return () -> after.apply(this.get());
    }

}
