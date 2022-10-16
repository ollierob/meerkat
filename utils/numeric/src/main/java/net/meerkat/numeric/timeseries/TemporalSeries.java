package net.meerkat.numeric.timeseries;

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
    T next(T temporal);

    @CheckForNull
    V at(T key);

    @CheckForNull
    default V at(final T key, final Interpolator<T, V> interpolator) {
        return interpolator.interpolate(key, this);
    }

}
