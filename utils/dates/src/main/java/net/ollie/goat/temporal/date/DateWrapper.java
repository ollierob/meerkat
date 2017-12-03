package net.ollie.goat.temporal.date;

import net.ollie.goat.temporal.PartialDateTime;

import javax.annotation.Nonnull;
import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Ollie
 */
public abstract class DateWrapper implements PartialDateTime {

    private final LocalDate date;
    private final ZoneId zoneId;

    protected DateWrapper(final LocalDate date) {
        this(date, ZoneOffset.UTC);
    }

    protected DateWrapper(final LocalDate date, final ZoneId zoneId) {
        this.date = date;
        this.zoneId = zoneId;
    }

    @Override
    public LocalDate date() {
        return date;
    }

    @Nonnull
    @Override
    public ZoneId zoneId() {
        return zoneId;
    }

    @Override
    public Optional<LocalTime> time() {
        return Optional.empty();
    }

    @Override
    public long until(final Temporal endExclusive, final TemporalUnit unit) {
        return date.until(endExclusive, unit);
    }

    @Override
    public Period until(final ChronoLocalDate endDateExclusive) {
        return date.until(endDateExclusive);
    }

    @Override
    public long getLong(final TemporalField field) {
        return date.getLong(field);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.date);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final DateWrapper other = (DateWrapper) obj;
        return this.is(other.date);
    }

    @Override
    public String toString() {
        return date.toString() + ":" + zoneId.toString();
    }
}
