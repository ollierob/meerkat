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
    default V simpleMovingAverage(T key, final int limit, final AdditionArithmetic<V> add, final MultiplicationArithmetic<V> mult) {
        key = this.prevOrEqual(key);
        if (key == null) return null;
        var sum = this.at(key);
        for (var i = 1; i < limit; i++) {
            key = this.prev(key);
            if (key == null) break;
            final var value = this.at(key);
            sum = add.add(sum, value);
        }
        return mult.divide(sum, limit);
    }

    @CheckForNull
    default V exponentialMovingAverage(T key, final double weight, final AdditionArithmetic<V> add, final MultiplicationArithmetic<V> mult) {
        key = this.prevOrEqual(key);
        if (key == null) return null;
        V sum = mult.multiply(this.at(key), weight);
        final var coeff = 1 - weight;
        while ((key = this.prev(key)) != null) {
            final var prev = this.at(key);
            sum = add.add(sum, mult.multiply(prev, coeff));
        }
        return sum;
    }

}
