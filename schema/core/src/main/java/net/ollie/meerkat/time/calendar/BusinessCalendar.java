package net.ollie.meerkat.time.calendar;

import java.time.LocalDate;
import java.util.SortedSet;

import javax.annotation.Nonnull;

import net.ollie.meerkat.time.interim.Interval;

/**
 *
 * @author Ollie
 */
public interface BusinessCalendar {

    @Nonnull
    SortedSet<LocalDate> holidaysIn(LocalDate startInclusive, LocalDate endExclusive);

    default boolean isHoliday(final LocalDate date) {
        return this.holidaysIn(date, date.plusDays(1)).contains(date);
    }

    default SortedSet<LocalDate> holidaysIn(final Interval interval) {
        return this.holidaysIn(interval.startInclusive(), interval.endExclusive());
    }

}
