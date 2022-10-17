package net.meerkat.numeric.timeseries;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.time.Instant;
import java.util.function.Function;

public record TransformedTimeSeries<V1, V2>(TimeSeries<V1> underlying, Function<? super V1, ? extends V2> operator)
        implements TimeSeries<V2> {

    @Nonnull
    @Override
    public Instant start() {
        return underlying.start();
    }

    @Nonnull
    @Override
    public Instant end() {
        return underlying.end();
    }

    @CheckForNull
    @Override
    public Instant prev(final Instant temporal) {
        return underlying.prev(temporal);
    }

    @CheckForNull
    @Override
    public Instant prevOrEqual(@Nonnull final Instant temporal) {
        return underlying.prev(temporal);
    }

    @CheckForNull
    @Override
    public Instant next(final Instant temporal) {
        return underlying.next(temporal);
    }

    @CheckForNull
    @Override
    public V2 at(final Instant time) {
        final var v = underlying.at(time);
        return v != null ? operator().apply(v) : null;
    }

}
