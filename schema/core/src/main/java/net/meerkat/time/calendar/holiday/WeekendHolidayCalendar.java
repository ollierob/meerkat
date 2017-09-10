package net.meerkat.time.calendar.holiday;

import java.time.LocalDate;

import net.meerkat.time.calendar.DateOutOfRangeException;

/**
 *
 * @author Ollie
 */
public class WeekendHolidayCalendar implements HolidayCalendar {

    @Override
    public boolean isInRange(final LocalDate date) {
        return true;
    }

    @Override
    public boolean is(final LocalDate date) throws DateOutOfRangeException {
        switch (date.getDayOfWeek()) {
            case SATURDAY:
            case SUNDAY:
                return true;
            default:
                return false;
        }
    }

    @Override
    public Holiday next(final LocalDate date) throws DateOutOfRangeException {
        throw new UnsupportedOperationException();
    }

}
