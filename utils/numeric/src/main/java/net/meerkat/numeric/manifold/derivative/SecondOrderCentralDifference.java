package net.meerkat.numeric.manifold.derivative;

import net.meerkat.numeric.Arithmetic;
import net.meerkat.numeric.interpolation.Interpolator;

import java.util.NavigableMap;
import java.util.function.BiFunction;

public class SecondOrderCentralDifference<X, Y, DY> extends AbstractCentralDifference<X, Y, DY> {

    private final X twoH;
    private final Arithmetic<Y> yArithmetic;

    public SecondOrderCentralDifference(
            final X step,
            final Arithmetic<X> xArithmetic,
            final Arithmetic<Y> yArithmetic,
            final Interpolator<X, Y> interpolator,
            final BiFunction<Y, X, DY> division) {
        super(step, xArithmetic, interpolator, division);
        this.yArithmetic = yArithmetic;
        this.twoH = xArithmetic.add(step, step);
    }

    @Override
    protected Y numerator(final X x, final NavigableMap<X, Y> map) {
        final Y y1 = y(x, 1, map);
        final Y y2 = y(x, -1, map);
        return yArithmetic.subtract(y1, y2);
    }

    @Override
    protected X denominator() {
        return twoH;
    }

}
