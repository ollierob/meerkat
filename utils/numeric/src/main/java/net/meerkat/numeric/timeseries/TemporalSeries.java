package net.meerkat.numeric.timeseries;

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
    V at(T temporal);

}
