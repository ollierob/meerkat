package net.meerkat.numeric.manifold.derivative;

import net.meerkat.numeric.manifold.Curve;

import javax.annotation.Nonnull;
import java.util.NavigableMap;

public interface Differentiation<X, Y, DY> {

    @Nonnull
    default Curve<X, DY> differentiate(@Nonnull final Curve<X, Y> curve) {
        return Curve.of(this.differentiate(curve.toMap()));
    }

    @Nonnull
    NavigableMap<X, DY> differentiate(@Nonnull NavigableMap<X, Y> map);

    interface HigherOrder<X, Y> extends Differentiation<X, Y, Y> {

        default Curve<X, Y> differentiate(final Curve<X, Y> curve, final int order) {
            Curve<X, Y> differentiated = curve;
            for (int i = 0; i < order; i++) {
                differentiated = this.differentiate(differentiated);
            }
            return differentiated;
        }

    }

}
