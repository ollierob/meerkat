package net.ollie.goat.suppliers.lazy;

import net.ollie.goat.suppliers.NonNullSupplier;

/**
 * @author Ollie
 */
public class LoadOnceNonNullLazy<T> implements Lazy<T> {

    private final NonNullSupplier<? extends T> supplier;
    private T value;

    public LoadOnceNonNullLazy(final NonNullSupplier<? extends T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public T get() {
        return value == null ? (value = supplier.get()) : value;
    }

}
