package net.meerkat.time.calendar.holiday;

import java.time.LocalDate;

import net.meerkat.time.calendar.DateOutOfRangeException;

/**
 *
 * @author ollie
 */
public class AnyHolidayCalendar implements HolidayCalendar {

    private final HolidayCalendar[] calendars;

    public AnyHolidayCalendar(final HolidayCalendar... calendars) {
        this.calendars = calendars;
    }

    @Override
    public boolean isInRange(final LocalDate date) {
        for (int i = 0; i < calendars.length; i++) {
            if (!calendars[i].isInRange(date)) {
                return false;
            }
        }
        return calendars.length > 0;
    }

    @Override
    public Holiday next(final LocalDate date) throws DateOutOfRangeException {
        Holiday earliest = null;
        for (int i = 0; i < calendars.length; i++) {
            final Holiday next = calendars[i].next(date);
            earliest = earliest == null || earliest.isAfter(next) ? next : earliest;
        }
        return earliest;
    }

}
