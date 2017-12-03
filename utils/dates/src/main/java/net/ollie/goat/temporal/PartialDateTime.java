package net.ollie.goat.temporal;

import net.ollie.goat.temporal.date.HasDate;

import javax.annotation.Nonnull;
import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoPeriod;
import java.time.chrono.IsoChronology;
import java.util.Optional;

/**
 * A date and time zone, and maybe also a time.
 */
public interface PartialDateTime extends HasDate, ChronoLocalDate {

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

    @Override
    default IsoChronology getChronology() {
        return this.date().getChronology();
    }

    @Override
    default int lengthOfMonth() {
        return this.date().lengthOfMonth();
    }

    @Override
    default ChronoPeriod until(ChronoLocalDate endDateExclusive) {
        return this.date().until(endDateExclusive);
    }

}
