package net.meerkat.numeric;

import javax.annotation.Nonnull;
import java.util.function.BinaryOperator;
import java.util.function.DoubleFunction;

public interface Arithmetic<T> {

    @Nonnull
    T add(@Nonnull T left, @Nonnull T right);

    @Nonnull
    T subtract(@Nonnull T minuend, @Nonnull T subtrahend);

    static <T extends Numeric.Summable<T>> Arithmetic<T> numeric() {
        return of(T::plus, T::minus);
    }

    static <T extends Number> Arithmetic<T> doublePrecision(final DoubleFunction<? extends T> fromDouble) {
        return of((l, r) -> fromDouble.apply(l.doubleValue() + r.doubleValue()), (l, r) -> fromDouble.apply(l.doubleValue() - r.doubleValue()));
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
