package net.meerkat.numeric.manifold.derivative;

import net.meerkat.numeric.Arithmetic;
import net.meerkat.numeric.interpolation.Interpolator;

import java.util.NavigableMap;
import java.util.function.BiFunction;

public class FourthOrderCentralDifference<X, Y, DY> extends AbstractCentralDifference<X, Y, DY> {

    private final Arithmetic<Y> yArithmetic;
    private final X twelveH;

    public FourthOrderCentralDifference(
            final X step,
            final Arithmetic<X> xArithmetic,
            final Arithmetic<Y> yArithmetic,
            final Interpolator<X, Y> interpolator,
            final BiFunction<Y, X, DY> division) {
        super(step, xArithmetic, interpolator, division);
        this.yArithmetic = yArithmetic;
        this.twelveH = xArithmetic.multiply(step, 12);
    }

    @Override
    protected Y numerator(final X x, final NavigableMap<X, Y> map) {
        final Y y1 = y(x, +2, map);
        final Y y2 = yArithmetic.multiply(y(x, +1, map), 8);
        final Y y3 = yArithmetic.multiply(y(x, -1, map), 8);
        final Y y4 = y(x, -2, map);
        //-f(x+2h) + 8f(x+h) - 8f(x-h) + f(x-2h)
        return yArithmetic.add(yArithmetic.subtract(y2, y1), yArithmetic.subtract(y4, y3));
    }

    @Override
    protected X denominator() {
        return twelveH;
    }

}
