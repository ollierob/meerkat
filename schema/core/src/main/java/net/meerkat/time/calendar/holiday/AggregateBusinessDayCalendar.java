package net.meerkat.time.calendar.holiday;

import java.time.LocalDate;
import java.util.Collection;

/**
 *
 * @author ollie
 */
public class AggregateBusinessDayCalendar implements HolidayCalendar {

    private final HolidayCalendar[] calendars;

    public AggregateBusinessDayCalendar(final Collection<HolidayCalendar> calendars) {
        this.calendars = calendars.toArray(new HolidayCalendar[calendars.size()]);
    }

    @Override
    public boolean isSupported(final LocalDate date) {
        for (final HolidayCalendar calendar : calendars) {
            if (!calendar.isSupported(date)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isHoliday(final LocalDate date) {
        for (final HolidayCalendar calendar : calendars) {
            if (calendar.isHoliday(date)) {
                return true;
            }
        }
        return false;
    }

}
