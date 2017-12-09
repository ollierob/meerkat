package net.meerkat.numeric.manifold;

import net.meerkat.numeric.Arithmetic;
import net.meerkat.numeric.interpolation.Interpolator;
import net.meerkat.numeric.manifold.derivative.Differentiation;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author Ollie
 */
public interface Curve<X, Y> {

    @CheckForNull
    Y at(X x);

    @CheckForNull
    Y get(X x, Interpolator<X, Y> interpolator);

    @Nonnull
    NavigableMap<X, Y> toMap();

    default boolean isEmpty() {
        return this.toMap().isEmpty();
    }

    default Set<X> xAxis() {
        return this.toMap().keySet();
    }

    default Points<Y> yAxis() {
        return Points.of(this.toMap().values());
    }

    default Collection<Entry<X, Y>> points() {
        return this.toMap().entrySet();
    }

    default Curve<X, Y> plus(final Curve<X, Y> that, final Interpolator<X, Y> interpolator, final Arithmetic<Y> arithmetic) {
        final NavigableMap<X, Y> thisMap = this.toMap();
        final NavigableMap<X, Y> thatMap = that.toMap();
        final NavigableMap<X, Y> outMap = new TreeMap<>();
        thisMap.forEach((x, y) -> outMap.put(x, arithmetic.add(y, interpolator.interpolate(x, thatMap))));
        thatMap.forEach((x, y) -> outMap.put(x, arithmetic.add(y, interpolator.interpolate(x, thisMap))));
        return of(outMap);
    }

    default <Z> Curve<X, Z> derivative(final Differentiation<X, Y, Z> differentiation) {
        return differentiation.differentiate(this);
    }

    default boolean isMonotonicallyIncreasing(final Comparator<? super Y> comparator) {
        return this.yAxis().isIncreasing(comparator);
    }

    static <X, Y> Curve<X, Y> of(final NavigableMap<X, Y> map) {
        return new MappedCurve<>(map);
    }

}
