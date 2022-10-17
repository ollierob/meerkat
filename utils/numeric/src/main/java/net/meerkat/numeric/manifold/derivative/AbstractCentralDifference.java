package net.meerkat.numeric.manifold.derivative;

import net.meerkat.numeric.AdditionArithmetic;
import net.meerkat.numeric.interpolation.Interpolator;

import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.function.BiFunction;

public abstract class AbstractCentralDifference<X, Y, DY> implements FiniteDifference<X, Y, DY> {

    private final X step;
    private final AdditionArithmetic<X> xArithmetic;
    private final Interpolator<X, Y> interpolator;
    private final BiFunction<Y, X, DY> division;

    protected AbstractCentralDifference(X step, AdditionArithmetic<X> xArithmetic, Interpolator<X, Y> interpolator, BiFunction<Y, X, DY> division) {
        this.step = step;
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

    protected Y y(final X x, final int numSteps, final NavigableMap<X, Y> map) {
        final X h = xArithmetic.multiply(step, numSteps);
        return interpolator.interpolate(xArithmetic.add(x, h), map);
    }

    protected abstract Y numerator(X x, NavigableMap<X, Y> map);

    protected abstract X denominator();

    protected DY differentiate(final X x, final NavigableMap<X, Y> map) {
        return division.apply(this.numerator(x, map), this.denominator());
    }

}
