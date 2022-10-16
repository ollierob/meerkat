package net.meerkat.numeric.timeseries;

import java.time.Instant;
import java.util.function.Function;

/**
 * @param <V> value type
 */
public interface TimeSeries<V> extends TemporalSeries<Instant, V> {

    default <V2> TimeSeries<V2> transform(final Function<? super V, ? extends V2> operator) {
        return new TransformedTimeSeries<>(this, operator);
    }

}

