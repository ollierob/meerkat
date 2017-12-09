package net.meerkat.numeric.manifold.derivative;

import net.meerkat.numeric.Arithmetic;
import net.meerkat.numeric.interpolation.Interpolator;

import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.function.BiFunction;

public class SecondOrderCentralDifference<X, Y, DY> implements FiniteDifference<X, Y, DY> {

    private final X step, twoStep;
    private final Arithmetic<X> xArithmetic;
    private final Arithmetic<Y> yArithmetic;
    private final Interpolator<X, Y> interpolator;
    private final BiFunction<Y, X, DY> division;

    public SecondOrderCentralDifference(
            final X step,
            final Arithmetic<X> xArithmetic,
            final Arithmetic<Y> yArithmetic,
            final Interpolator<X, Y> interpolator,
            final BiFunction<Y, X, DY> division) {
        this.step = step;
        this.yArithmetic = yArithmetic;
        this.twoStep = xArithmetic.add(step, step);
        this.xArithmetic = xArithmetic;
        this.interpolator = interpolator;
        this.division = division;
    }

    @Override
    public NavigableMap<X, DY> differentiate(final NavigableMap<X, Y> map) {
        final NavigableMap<X, DY> out = new TreeMap<>();
        map.forEach((x, y) -> out.put(x, this.differentiate(x, map)));
        return out;
    }

    protected DY differentiate(final X x, final NavigableMap<X, Y> map) {
        final Y y1 = interpolator.interpolate(xArithmetic.add(x, step), map);
        final Y y2 = interpolator.interpolate(xArithmetic.subtract(x, step), map);
        return division.apply(yArithmetic.subtract(y1, y2), twoStep);
    }

}
