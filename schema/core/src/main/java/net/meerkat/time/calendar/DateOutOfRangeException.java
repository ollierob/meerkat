package net.meerkat.time.calendar;

import java.time.LocalDate;

/**
 *
 * @author Ollie
 */
public class DateOutOfRangeException extends InvalidDateException {

    private static final long serialVersionUID = 1L;

    public DateOutOfRangeException(final LocalDate date) {
        super(date);
    }

}
