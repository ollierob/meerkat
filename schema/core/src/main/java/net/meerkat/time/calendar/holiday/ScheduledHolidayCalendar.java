package net.meerkat.time.calendar.holiday;

import java.time.LocalDate;
import java.util.NavigableMap;

import net.meerkat.time.calendar.DateOutOfRangeException;
import net.ollie.goat.temporal.date.Dates;

/**
 *
 * @author Ollie
 */
public class ScheduledHolidayCalendar implements HolidayCalendar {

    private final LocalDate validFrom;
    private final LocalDate validTo;
    private final NavigableMap<LocalDate, Holiday> holidays;

    public ScheduledHolidayCalendar(final LocalDate validFrom, final LocalDate validTo, final NavigableMap<LocalDate, Holiday> holidays) {
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.holidays = holidays;
    }

    @Override
    public boolean contains(final LocalDate date) {
        this.checkInRange(date);
        return holidays.containsKey(date);
    }

    private void checkInRange(final LocalDate date) throws DateOutOfRangeException {
        if (!this.isInRange(date)) {
            throw new DateOutOfRangeException(date);
        }
    }

    @Override
    public boolean isInRange(final LocalDate date) {
        return Dates.areOrdered(validFrom, date, validTo);
    }

    @Override
    public Holiday next(final LocalDate date) throws DateOutOfRangeException {
        this.checkInRange(date);
        return holidays.ceilingEntry(date).getValue();
    }

    @Override
    public Holiday previous(final LocalDate date) {
        throw new UnsupportedOperationException(); //TODO
    }

}
