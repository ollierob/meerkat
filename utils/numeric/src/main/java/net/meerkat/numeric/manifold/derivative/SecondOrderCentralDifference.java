package net.meerkat.numeric.manifold.derivative;

import net.meerkat.numeric.Arithmetic;
import net.meerkat.numeric.interpolation.Interpolator;

import java.util.NavigableMap;
import java.util.TreeMap;

public abstract class SecondOrderCentralDifference<X, Y, Z> implements FiniteDifference<X, Y, Z> {

    private final X step, twoStep;
    private final Arithmetic<X> xArithmetic;
    private final Arithmetic<Y> yArithmetic;
    private final Interpolator<X, Y> interpolator;

    public SecondOrderCentralDifference(
            final X step,
            final Arithmetic<X> xArithmetic,
            final Arithmetic<Y> yArithmetic,
            final Interpolator<X, Y> interpolator) {
        this.step = step;
        this.yArithmetic = yArithmetic;
        this.twoStep = xArithmetic.add(step, step);
        this.xArithmetic = xArithmetic;
        this.interpolator = interpolator;
    }

    @Override
    public NavigableMap<X, Z> differentiate(final NavigableMap<X, Y> map) {
        final NavigableMap<X, Z> out = new TreeMap<>();
        map.forEach((x, y) -> out.put(x, this.differentiate(x, map)));
        return out;
    }

    protected Z differentiate(final X x, final NavigableMap<X, Y> map) {
        final Y y1 = interpolator.interpolate(xArithmetic.add(x, step), map);
        final Y y2 = interpolator.interpolate(xArithmetic.subtract(x, step), map);
        return this.divide(yArithmetic.subtract(y1, y2), twoStep);
    }

    protected abstract Z divide(Y numerator, X denominator);

}
