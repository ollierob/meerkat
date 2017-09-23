package net.meerkat.time.calendar.business;


import java.time.LocalDate;

import net.meerkat.time.calendar.Calendar;
import net.meerkat.time.calendar.DateOutOfRangeException;

/**
 *
 * @author Ollie
 */
public interface BusinessDayCalendar extends Calendar<BusinessDay> {

    @Override
    @Deprecated
    default boolean contains(final LocalDate date) throws DateOutOfRangeException {
        return this.isBusinessDay(date);
    }

    default boolean isBusinessDay(final LocalDate date) throws DateOutOfRangeException {
        return Calendar.super.contains(date);
    }
    
}
