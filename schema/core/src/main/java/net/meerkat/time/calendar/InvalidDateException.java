package net.meerkat.time.calendar;

import net.meerkat.temporal.date.HasDate;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

/**
 *
 * @author Ollie
 */
public class InvalidDateException extends RuntimeException implements HasDate {

    private static final long serialVersionUID = 1L;

    private final ChronoLocalDate date;

    public InvalidDateException(final ChronoLocalDate date) {
        this(date + " is invalid!", date);
    }

    protected InvalidDateException(final String message, final ChronoLocalDate date) {
        super(message);
        this.date = date;
    }

    @Override
    public LocalDate date() {
        return LocalDate.from(date);
    }

}
