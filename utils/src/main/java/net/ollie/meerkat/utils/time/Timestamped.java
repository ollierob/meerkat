package net.ollie.meerkat.utils.time;

import java.time.Instant;

/**
 *
 * @author Ollie
 */
public class Timestamped<T> {

    private final Instant timestamp;
    private final T value;

    public Timestamped(final Instant timestamp, final T value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    public Instant timestamp() {
        return timestamp;
    }

    public T value() {
        return value;
    }

}
