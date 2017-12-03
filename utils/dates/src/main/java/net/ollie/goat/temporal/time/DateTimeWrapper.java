package net.ollie.goat.temporal.time;

import net.ollie.goat.temporal.PartialDateTime;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.Optional;

public abstract class DateTimeWrapper implements PartialDateTime {

    private final ZonedDateTime zonedDateTime;

    protected DateTimeWrapper(final ZonedDateTime zonedDateTime) {
        this.zonedDateTime = zonedDateTime;
    }

    @Nonnull
    @Override
    public LocalDate date() {
        return zonedDateTime.toLocalDate();
    }

    @Nonnull
    @Override
    public ZoneId zoneId() {
        return zonedDateTime.getZone();
    }

    @Override
    public Optional<LocalTime> time() {
        return Optional.of(zonedDateTime.toLocalTime());
    }

    @Nonnull
    @Override
    @Deprecated
    public Optional<ZonedDateTime> toDateTime() {
        return Optional.of(zonedDateTime);
    }

    @Nonnull
    public ZonedDateTime zonedDateTime() {
        return zonedDateTime;
    }

    @Override
    public long until(final Temporal endExclusive, final TemporalUnit unit) {
        return zonedDateTime.until(endExclusive, unit);
    }

    @Override
    public long getLong(final TemporalField field) {
        return zonedDateTime.getLong(field);
    }

    @Override
    public String toString() {
        return zonedDateTime.toString();
    }

}
