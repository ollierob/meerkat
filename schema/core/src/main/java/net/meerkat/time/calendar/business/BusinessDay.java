package net.meerkat.time.calendar.business;

import java.time.LocalDate;

import net.ollie.goat.temporal.date.DateWrapper;

/**
 *
 * @author Ollie
 */
public class BusinessDay extends DateWrapper {

    protected BusinessDay(final LocalDate date) {
        super(date);
    }

}
