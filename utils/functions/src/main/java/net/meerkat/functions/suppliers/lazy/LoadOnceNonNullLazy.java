package net.meerkat.functions.suppliers.lazy;

import net.meerkat.functions.suppliers.NonNullSupplier;

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
