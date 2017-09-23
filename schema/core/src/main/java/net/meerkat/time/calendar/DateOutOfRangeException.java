package net.meerkat.time.calendar;

import java.time.chrono.ChronoLocalDate;

/**
 *
 * @author Ollie
 */
public class DateOutOfRangeException extends InvalidDateException {

    private static final long serialVersionUID = 1L;

    public DateOutOfRangeException(final ChronoLocalDate date) {
        super(date + " is out of range!", date);
    }

}
