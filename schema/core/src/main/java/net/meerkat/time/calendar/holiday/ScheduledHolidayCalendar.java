package net.meerkat.time.calendar.holiday;

import java.time.LocalDate;
import java.util.NavigableMap;

import net.meerkat.time.calendar.DateOutOfRangeException;
import net.ollie.goat.temporal.date.interim.CompleteInterval;

/**
 *
 * @author Ollie
 */
public class ScheduledHolidayCalendar implements HolidayCalendar {

    private final CompleteInterval range;
    private final NavigableMap<LocalDate, Holiday> holidays;

    public ScheduledHolidayCalendar(final CompleteInterval range, final NavigableMap<LocalDate, Holiday> holidays) {
        this.range = range;
        this.holidays = holidays;
    }

    @Override
    public boolean contains(final LocalDate date) {
        this.requireInRange(date);
        return holidays.containsKey(date);
    }

    @Override
    public boolean isInRange(final LocalDate date) {
        return range.contains(date);
    }

    @Override
    public Holiday next(final LocalDate date) throws DateOutOfRangeException {
        this.requireInRange(date);
        return holidays.ceilingEntry(date).getValue();
    }

    @Override
    public Holiday previous(final LocalDate date) {
        throw new UnsupportedOperationException(); //TODO
    }

}
