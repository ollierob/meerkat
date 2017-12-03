package net.meerkat.time.calendar.holiday;

import net.coljate.map.Entry;
import net.coljate.map.Map;
import net.coljate.map.SortedMap;
import net.coljate.set.Set;
import net.meerkat.functions.Functions;
import net.meerkat.temporal.date.interim.CompleteInterval;
import net.meerkat.time.calendar.DateOutOfRangeException;

import java.time.LocalDate;
import java.util.Comparator;

/**
 *
 * @author Ollie
 */
public class ScheduledHolidayCalendar implements HolidayCalendar {

    public static ScheduledHolidayCalendar of(final Set<Holiday> holidays) {
        return new ScheduledHolidayCalendar(Map.mapFirstKey(holidays, Holiday::date));
    }

    private final CompleteInterval range;
    private final SortedMap<LocalDate, Holiday> holidays;

    protected ScheduledHolidayCalendar(final Map<LocalDate, Holiday> holidays) {
        this(holidays.sortKeys(Comparator.naturalOrder()));
    }

    protected ScheduledHolidayCalendar(final SortedMap<LocalDate, Holiday> holidays) {
        this.holidays = holidays;
        this.range = new CompleteInterval(holidays.keys().first(), holidays.keys().last());
    }

    @Override
    public boolean isHoliday(final LocalDate date) throws DateOutOfRangeException {
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
        return Functions.ifNonNull(holidays.ceilingEntry(date), Entry::value);
    }

    @Override
    public Holiday previous(final LocalDate date) {
        throw new UnsupportedOperationException(); //TODO
    }

}
