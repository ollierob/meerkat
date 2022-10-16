package net.meerkat.numeric.timeseries;

import javax.annotation.Nonnull;
import java.time.Duration;
import java.time.Instant;

/**
 * @param <V> value type
 */
public interface TimeSeries<V> extends TemporalSeries<Instant, V> {

    @Nonnull
    Duration increment();

}

