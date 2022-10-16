package net.meerkat.numeric;

import java.util.function.UnaryOperator;

public interface Logarithm<V> extends UnaryOperator<V> {

    double base();

}
