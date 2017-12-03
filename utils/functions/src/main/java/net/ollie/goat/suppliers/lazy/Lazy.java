package net.ollie.goat.suppliers.lazy;

import net.ollie.goat.suppliers.NonNullSupplier;

import java.util.function.Supplier;

/**
 * @author Ollie
 */
public interface Lazy<T> extends Supplier<T> {

    static <T> Lazy<T> now(final T value) {
        return () -> value;
    }

    static <T> Lazy<T> loadOnce(final Supplier<? extends T> supplier) {
        return new LoadOnceVolatileLazy<>(supplier);
    }

    static <T> Lazy<T> loadOnceNonNull(final NonNullSupplier<? extends T> supplier) {
        return new LoadOnceNonNullLazy<>(supplier);
    }

}
