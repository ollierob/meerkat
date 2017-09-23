package net.meerkat.time.calendar.holiday;

import java.time.LocalDate;

import net.meerkat.time.calendar.Calendar;
import net.meerkat.time.calendar.DateOutOfRangeException;

/**
 *
 * @author Ollie
 */
public interface HolidayCalendar extends Calendar<Holiday> {

    @Override
    @Deprecated
    default boolean contains(final LocalDate date) throws DateOutOfRangeException {
        return this.isHoliday(date);
    }

    default boolean isHoliday(final LocalDate date) throws DateOutOfRangeException {
        return Calendar.super.contains(date);
    }

}
