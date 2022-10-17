package net.meerkat.numeric;

public interface MultiplicationArithmetic<T> {

    T multiply(T d1, double d2);

    default T divide(final T d1, final double d2) {
        return this.multiply(d1, 1 / d2);
    }

}
