package net.meerkat.numeric;

import javax.annotation.Nonnull;
import java.util.function.BinaryOperator;

public interface Arithmetic<T> {

    @Nonnull
    T add(@Nonnull T left, @Nonnull T right);

    @Nonnull
    T subtract(@Nonnull T minuend, @Nonnull T subtrahend);

    static <T extends Numeric.Summable<T>> Arithmetic<T> numeric() {
        return new Arithmetic<T>() {

            @Override
            public T add(final T left, final T right) {
                return left.plus(right);
            }

            @Override
            public T subtract(T minuend, T subtrahend) {
                return minuend.minus(subtrahend);
            }
        };
    }

    static <T> Arithmetic<T> of(final BinaryOperator<T> addition, final BinaryOperator<T> subtraction) {
        return new Arithmetic<T>() {

            @Override
            public T add(T left, T right) {
                return addition.apply(left, right);
            }

            @Override
            public T subtract(T minuend, T subtrahend) {
                return subtraction.apply(minuend, subtrahend);
            }

        };
    }

}
