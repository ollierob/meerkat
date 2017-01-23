package net.meerkat.utils.time;

import java.time.Instant;
import java.time.LocalDate;

import net.meerkat.utils.SelfTyped;

/**
 *
 * @author Ollie
 */
public interface Timestampable<T extends SelfTyped<T>> extends SelfTyped<T> {

    default Timestamped<Instant, T> at(final Instant instant) {
        return new Timestamped<>(instant, self());
    }

    default Timestamped<LocalDate, T> on(final LocalDate date) {
        return new Timestamped<>(date, self());
    }

}
