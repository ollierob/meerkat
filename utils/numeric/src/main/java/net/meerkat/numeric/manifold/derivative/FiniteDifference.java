package net.meerkat.numeric.manifold.derivative;

import net.meerkat.numeric.manifold.Curve;

import java.util.NavigableMap;

public interface FiniteDifference<X, Y, Z> {

    default Curve<X, Z> differentiate(final Curve<X, Y> curve) {
        return Curve.of(this.differentiate(curve.toMap()));
    }

    NavigableMap<X, Z> differentiate(NavigableMap<X, Y> map);

}
