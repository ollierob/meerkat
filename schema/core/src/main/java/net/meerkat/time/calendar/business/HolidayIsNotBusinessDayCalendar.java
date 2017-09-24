package net.meerkat.time.calendar.business;

import java.time.LocalDate;

import net.meerkat.time.calendar.DateOutOfRangeException;
import net.meerkat.time.calendar.holiday.HolidayCalendar;

/**
 *
 * @author ollie
 */
public class HolidayIsNotBusinessDayCalendar implements BusinessDayCalendar {

    private final HolidayCalendar holidayCalendar;
    private final BusinessDayCache cache;

    public HolidayIsNotBusinessDayCalendar(final HolidayCalendar holidayCalendar, final BusinessDayCache cache) {
        this.holidayCalendar = holidayCalendar;
        this.cache = cache;
    }

    @Override
    public boolean isInRange(final LocalDate date) {
        return holidayCalendar.isInRange(date);
    }

    @Override
    public BusinessDay next(final LocalDate date) throws DateOutOfRangeException {
        LocalDate current = date;
        while (holidayCalendar.isHoliday(current)) {
            current = current.plusDays(1);
        }
        return cache.get(current);
    }

}
