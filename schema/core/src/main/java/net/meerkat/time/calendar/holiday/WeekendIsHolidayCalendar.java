package net.meerkat.time.calendar.holiday;

import java.time.DayOfWeek;
import java.time.LocalDate;

import net.coljate.set.EnumSet;
import net.meerkat.time.calendar.DateOutOfRangeException;

/**
 *
 * @author ollie
 */
public class WeekendIsHolidayCalendar implements HolidayCalendar {

    private static final EnumSet<DayOfWeek> SATURDAY_SUNDAY = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
    private static final EnumSet<DayOfWeek> FRIDAY_AND_SATURDAY = EnumSet.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
    private static final EnumSet<DayOfWeek> FRIDAY_TO_SUNDAY = EnumSet.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

    public static HolidayCalendar satSunToMon(final HolidayCache cache) {
        return new WeekendIsHolidayCalendar(SATURDAY_SUNDAY, cache);
    }

    private final EnumSet<DayOfWeek> weekend;
    private final HolidayCache cache;

    protected WeekendIsHolidayCalendar(final EnumSet<DayOfWeek> weekend, final HolidayCache cache) {
        this.weekend = weekend;
        this.cache = cache;
    }

    @Override
    public boolean isInRange(final LocalDate date) {
        return true;
    }

    @Override
    public boolean isHoliday(final LocalDate date) throws DateOutOfRangeException {
        return this.isWeekend(date);
    }

    boolean isWeekend(final LocalDate date) {
        return weekend.contains(date.getDayOfWeek());
    }

    @Override
    public Holiday next(final LocalDate date) throws DateOutOfRangeException {
        LocalDate current = date;
        while (!this.isWeekend(current)) {
            current = current.plusDays(1);
        }
        return cache.get(current, current.getDayOfWeek().name());
    }

}
