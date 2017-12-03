package net.meerkat.functions;

import net.meerkat.functions.suppliers.CheckedSupplier;

import javax.annotation.CheckForNull;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Ollie
 */
public final class Functions {

    private Functions() {
    }

    public static <T, R> R ifNonNull(final T object, final Function<? super T, ? extends R> transform) {
        return ifNonNull(object, transform, null);
    }

    public static <T, R> R ifNonNull(final T object, final Function<? super T, ? extends R> transform, final R valueIfNull) {
        return object == null ? valueIfNull : transform.apply(object);
    }

    public static <T> boolean ifBothNonNull(final T left, final T right, final BiPredicate<T, T> ifBothNonNull) {
        return left == null
                ? right == null
                : right != null && ifBothNonNull.test(left, right);
    }

    public static <T> T ifBothNonNull(final T left, final T right, final BinaryOperator<T> ifBothNonNull) {
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return ifBothNonNull.apply(left, right);
        }
    }

    public static <T, X extends Exception> T ifNull(final T object, final CheckedSupplier<? extends T, X> supplier) throws X {
        return object == null ? supplier.get() : object;
    }

    @CheckForNull
    public static <T, R> R iff(final T input, final Predicate<? super T> predicate, final Function<? super T, ? extends R> transform) {
        return predicate.test(input)
                ? transform.apply(input)
                : null;
    }

}
