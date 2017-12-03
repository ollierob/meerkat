package net.ollie.goat.suppliers;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * @author Ollie
 */
public final class Suppliers {

    private Suppliers() {
    }

    public static <T> T firstNonNull(final T object1, final T object2) {
        return object1 == null ? object2 : object1;
    }

    public static <T> T firstNonNull(final T object, final Supplier<? extends T> supplier) {
        return object == null ? supplier.get() : object;
    }

    @Nonnull
    public static <T> T firstNonNull(@Nonnull final Supplier<? extends T> supplier, @Nonnull final T object) {
        requireNonNull(object);
        final T got = supplier.get();
        return got == null ? object : got;
    }

    @CheckForNull
    public static <T> T firstNonNull(@Nonnull final Supplier<? extends T> first, @Nonnull final Supplier<? extends T> second) {
        final T got = first.get();
        return got == null ? second.get() : got;
    }

}
