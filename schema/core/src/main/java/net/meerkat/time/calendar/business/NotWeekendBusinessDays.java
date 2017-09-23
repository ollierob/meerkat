package net.meerkat.time.calendar.business;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumSet;

import net.meerkat.time.calendar.DateOutOfRangeException;

/**
 *
 * @author Ollie
 */
public class NotWeekendBusinessDays implements BusinessDayCalendar {

    private static final EnumSet<DayOfWeek> SATURDAY_TO_SUNDAY = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

    public static NotWeekendBusinessDays satSunToMonday(final BusinessDayCache cache) {
        return new NotWeekendBusinessDays(SATURDAY_TO_SUNDAY, cache);
    }

    private final EnumSet<DayOfWeek> weekend;
    private final BusinessDayCache cache;

    private NotWeekendBusinessDays(final EnumSet<DayOfWeek> weekend, final BusinessDayCache cache) {
        this.weekend = weekend;
        this.cache = cache;
    }

    @Override
    public boolean contains(final LocalDate date) {
        return !this.isWeekend(date);
    }

    boolean isWeekend(final LocalDate date) {
        return weekend.contains(date.getDayOfWeek());
    }

    @Override
    public boolean isInRange(final LocalDate date) {
        return true;
    }

    @Override
    public BusinessDay next(final LocalDate date) throws DateOutOfRangeException {
        LocalDate current = date;
        while (this.isWeekend(current)) {
            current = current.plusDays(1);
        }
        return cache.get(date);
    }

}
