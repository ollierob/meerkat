package net.meerkat.time.calendar.business;

import net.meerkat.temporal.date.DateWrapper;

import java.time.LocalDate;

/**
 *
 * @author Ollie
 */
public class BusinessDay extends DateWrapper {

    protected BusinessDay(final LocalDate date) {
        super(date);
    }

}
