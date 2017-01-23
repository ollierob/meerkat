package net.meerkat.utils.time;

import java.time.Instant;

import net.meerkat.utils.SelfTyped;

/**
 *
 * @author Ollie
 */
public interface Timestampable<T extends SelfTyped<T>> extends SelfTyped<T> {

    default Timestamped<T> at(final Instant instant) {
        return new Timestamped<>(instant, self());
    }

}
