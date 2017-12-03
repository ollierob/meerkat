package net.meerkat.numeric;

import java.util.function.BinaryOperator;

public interface Arithmetic<T> {

    T add(T left, T right);

    T subtract(T minuend, T subtrahend);

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
