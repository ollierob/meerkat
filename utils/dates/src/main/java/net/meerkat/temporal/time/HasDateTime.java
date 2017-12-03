package net.meerkat.temporal.time;

import net.meerkat.temporal.date.HasDate;

import javax.annotation.Nonnull;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author Ollie
 */
public interface HasDateTime extends HasDate {

    @Nonnull
    Instant time();

    @Nonnull
    default ZonedDateTime dateTime() {
        return this.dateTime(ZoneId.systemDefault());
    }

    @Nonnull
    default ZonedDateTime dateTime(final ZoneId zone) {
        return ZonedDateTime.ofInstant(this.time(), zone);
    }

    @Override
    default LocalDate date() {
        return this.dateTime().toLocalDate();
    }

    static int compareByTime(final HasDateTime left, final HasDateTime right) {
        return left.time().compareTo(right.time());
    }

}
