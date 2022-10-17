package net.meerkat.numeric.manifold.derivative;

import net.meerkat.numeric.AdditionArithmetic;
import net.meerkat.numeric.interpolation.Interpolator;

public interface FiniteDifference<X, Y, DY> extends Differentiation<X, Y, DY> {

    static FiniteDifference<Double, Double, Double> secondOrderCentralDifference(final double step, final Interpolator<Double, Double> interpolator) {
        return new SecondOrderCentralDifference<>(
                step,
                AdditionArithmetic.doublePrecision(),
                AdditionArithmetic.doublePrecision(),
                interpolator,
                (d1, d2) -> d2 / d1);
    }

}
