package net.meerkat.time.calendar;

import java.time.LocalDate;

import net.ollie.goat.temporal.date.HasDate;

/**
 *
 * @author Ollie
 */
public class InvalidDateException extends RuntimeException implements HasDate {

    private static final long serialVersionUID = 1L;

    private final LocalDate date;

    public InvalidDateException(final LocalDate date) {
        super(date + " is invalid!");
        this.date = date;
    }

    @Override
    public LocalDate date() {
        return date;
    }

}
