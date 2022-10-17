package net.meerkat.numeric.timeseries;

import net.meerkat.numeric.AdditionArithmetic;
import net.meerkat.numeric.MultiplicationArithmetic;
import net.meerkat.numeric.interpolation.Interpolator;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

public interface TemporalSeries<T, V> {

    @Nonnull
    T start();

    @Nonnull
    T end();

    @CheckForNull
    T prev(T temporal);

    @CheckForNull
    T next(@Nonnull T temporal);

    @CheckForNull
    T prevOrEqual(@Nonnull T temporal);

    @CheckForNull
    V at(T key);

    @CheckForNull
    default V at(final T key, final Interpolator<T, V> interpolator) {
        return interpolator.interpolate(key, this);
    }

    @CheckForNull
    default V exponentialMovingAverage(T key, final double weight, final AdditionArithmetic<V> add, final MultiplicationArithmetic<V> times) {
        key = this.prevOrEqual(key);
        if (key == null) return null;
        V sum = times.multiply(this.at(key), weight);
        final var coeff = 1 - weight;
        while ((key = this.prev(key)) != null) {
            final var prev = this.at(key);
            sum = add.add(sum, times.multiply(prev, coeff));
        }
        return sum;
    }

}
