package net.meerkat.time.calendar.business;

import java.time.LocalDate;

import net.meerkat.time.calendar.Calendar;
import net.meerkat.time.calendar.DateOutOfRangeException;
import net.meerkat.time.calendar.holiday.HolidayCalendar;

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

    static BusinessDayCalendar notHoliday(final HolidayCalendar calendar, final BusinessDayCache cache) {
        return new BusinessDayIsNotHolidayCalendar(calendar, cache);
    }

    static BusinessDayCalendar notSatSun(final BusinessDayCache cache) {
        return BusinessDayIsNotWeekendCalendar.satSunToMon(cache);
    }

    static BusinessDayCalendar notFriSat(final BusinessDayCache cache) {
        return BusinessDayIsNotWeekendCalendar.friSatToSun(cache);
    }

    static BusinessDayCalendar notFriSatSun(final BusinessDayCache cache) {
        return BusinessDayIsNotWeekendCalendar.friSatSunToMon(cache);
    }

    static BusinessDayCalendar notHolidayOrWeekend(final HolidayCalendar calendar, final BusinessDayCache cache) {
        return both(notHoliday(calendar, cache), notSatSun(cache));
    }

    static BusinessDayCalendar both(final BusinessDayCalendar first, final BusinessDayCalendar second) {
        return new BusinessDayPairCalendar(first, second);
    }

}
