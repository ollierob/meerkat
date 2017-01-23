package net.meerkat.utils.time;

/**
 *
 * @author Ollie
 */
public class Timestamped<T, V> {

    private final T timestamp;
    private final V value;

    public Timestamped(final T timestamp, final V value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    public T timestamp() {
        return timestamp;
    }

    public V value() {
        return value;
    }

}
