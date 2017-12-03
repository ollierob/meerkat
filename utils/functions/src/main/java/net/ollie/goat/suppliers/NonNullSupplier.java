package net.ollie.goat.suppliers;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * @author Ollie
 * @see Supplier
 */
public interface NonNullSupplier<T> extends Supplier<T> {

    @Nonnull
    @Override
    T get();

    @CheckReturnValue
    static <T> NonNullSupplier<T> of(@Nonnull final Supplier<? extends T> supplier, @Nonnull final T defaultValue) {
        requireNonNull(defaultValue);
        return () -> Suppliers.firstNonNull(supplier, defaultValue);
    }

}
