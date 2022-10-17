package net.meerkat.numeric;

import javax.annotation.Nonnull;
import java.util.function.BinaryOperator;
import java.util.function.DoubleFunction;

public interface AdditionArithmetic<T> {

    @Nonnull
    T add(@Nonnull T left, @Nonnull T right);

    @Nonnull
    T subtract(@Nonnull T minuend, @Nonnull T subtrahend);

    @SuppressWarnings("unchecked")
    default T add(final T left, final T... rest) {
        T sum = left;
        for (final T r : rest) {
            sum = this.add(sum, r);
        }
        return sum;
    }

    default T zero(final T value) {
        return this.subtract(value, value);
    }

    default T negate(final T value) {
        return this.multiply(value, -1);
    }

    default T multiply(final T left, final int n) {
        if (n == 0) {
            return this.zero(left);
        }
        T sum = left;
        final int s = Math.abs(n);
        for (int i = 1; i < s; i++) {
            sum = this.add(sum, left);
        }
        return n > 0 ? sum : this.negate(sum);
    }

    static <T extends Numeric.Summable<T>> AdditionArithmetic<T> numeric() {
        return of(T::plus, T::minus);
    }

    static AdditionArithmetic<Double> doublePrecision() {
        return of(Double::sum, (a, b) -> a - b);
    }

    static <T extends Number> AdditionArithmetic<T> doublePrecision(final DoubleFunction<? extends T> fromDouble) {
        return of((l, r) -> fromDouble.apply(l.doubleValue() + r.doubleValue()), (l, r) -> fromDouble.apply(l.doubleValue() - r.doubleValue()));
    }

    static <T> AdditionArithmetic<T> of(final BinaryOperator<T> addition, final BinaryOperator<T> subtraction) {
        return new AdditionArithmetic<T>() {

            @Override
            public T add(final T left, final T right) {
                return addition.apply(left, right);
            }

            @Override
            public T subtract(final T minuend, final T subtrahend) {
                return subtraction.apply(minuend, subtrahend);
            }

        };
    }

}
