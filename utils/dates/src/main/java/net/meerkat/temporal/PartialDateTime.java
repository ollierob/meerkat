package net.meerkat.temporal;

import net.meerkat.temporal.date.DateWrapper;
import net.meerkat.temporal.date.HasDate;
import net.meerkat.temporal.time.DateTimeWrapper;

import javax.annotation.Nonnull;
import java.time.*;
import java.time.temporal.Temporal;
import java.util.Optional;

/**
 * A date and time zone, and maybe also a time.
 */
public interface PartialDateTime extends HasDate, Temporal {

    @Override
    @Nonnull
    LocalDate date();

    @Nonnull
    Optional<LocalTime> time();

    @Nonnull
    default ZoneId zoneId() {
        return ZoneOffset.UTC;
    }

    @Nonnull
    default Optional<LocalDateTime> toLocalDateTime() {
        return this.time().map(time -> LocalDateTime.of(this.date(), time));
    }

    @Nonnull
    default Optional<ZonedDateTime> toDateTime() {
        return this.time().map(time -> ZonedDateTime.of(this.date(), time, this.zoneId()));
    }

    default ZonedDateTime toDateTimeOr(final LocalTime time) {
        return this.toDateTime().orElseGet(() -> ZonedDateTime.of(this.date(), time, this.zoneId()));
    }

    default ZonedDateTime toDateTimeOrMidnight() {
        return this.toDateTimeOr(LocalTime.MIDNIGHT);
    }

    default ZonedDateTime toDateTimeOrNoon() {
        return this.toDateTimeOr(LocalTime.NOON);
    }

    static PartialDateTime ofUtcDate(final LocalDate date) {
        return new DateWrapper(date);
    }

    static PartialDateTime of(final ZonedDateTime dateTime) {
        return new DateTimeWrapper(dateTime);
    }

}
