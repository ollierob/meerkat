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

    private static final EnumSet<DayOfWeek> SATURDAY_SUNDAY = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
    private static final EnumSet<DayOfWeek> FRIDAY_AND_SATURDAY = EnumSet.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY);
    private static final EnumSet<DayOfWeek> FRIDAY_TO_SUNDAY = EnumSet.of(DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);

    /**
     * @return business days don't lie on a Saturday or Sunday. Common throughout Western markets.
     */
    public static NotWeekendBusinessDays satSunToMon(final BusinessDayCache cache) {
        return new NotWeekendBusinessDays(SATURDAY_SUNDAY, cache);
    }

    /**
     * @return business days don't lie on a Friday or Saturday. Common throughout Arab markets.
     */
    public static NotWeekendBusinessDays friSatToSun(final BusinessDayCache cache) {
        return new NotWeekendBusinessDays(FRIDAY_AND_SATURDAY, cache);
    }

    /**
     * @return business days don't lie on a Friday, Saturday or Sunday. Typical in some Arab markets.
     */
    public static NotWeekendBusinessDays friSatSunToMon(final BusinessDayCache cache) {
        return new NotWeekendBusinessDays(FRIDAY_TO_SUNDAY, cache);
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
