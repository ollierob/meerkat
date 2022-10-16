package net.meerkat.numeric.timeseries;

import java.time.Instant;

public interface DoubleTimeSeries extends TimeSeries<Double> {

    double doubleAt(Instant time);

    @Override
    default Double at(final Instant temporal) {
        return this.doubleAt(temporal);
    }
}
