package net.meerkat.time.calendar;

import java.time.LocalDate;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import net.ollie.goat.temporal.date.Dates;
import net.ollie.goat.temporal.date.interim.CompleteInterval;

/**
 *
 * @author Ollie
 */
public interface BusinessDayCalendar {

    boolean isHoliday(@Nonnull LocalDate date);

    default boolean isBusinessDay(@Nonnull final LocalDate date) {
        return !this.isHoliday(date);
    }

    default int countBusinessDays(final LocalDate start, final LocalDate end) {
        int count = 0;
        for (final LocalDate date : Dates.over(start, end)) {
            if (this.isBusinessDay(date)) {
                count++;
            }
        }
        return count;
    }

    default LocalDate nthBusinessDay(final LocalDate date, final int n) {
        final int signum = n > 0 ? +1 : -1;
        int c = 0;
        LocalDate current = date;
        while (c < n) {
            current = current.plusDays(signum);
            if (this.isBusinessDay(current)) {
                c++;
            }
        }
        return current;
    }

    @Nonnull
    default NavigableSet<LocalDate> holidaysIn(final LocalDate startInclusive, final LocalDate endExclusive) {
        final NavigableSet<LocalDate> holidays = new TreeSet<>();
        LocalDate date = startInclusive;
        while (date.isBefore(endExclusive)) {
            if (!this.isBusinessDay(date)) {
                holidays.add(date);
            }
        }
        return holidays;
    }

    @Nonnull
    default NavigableSet<LocalDate> holidaysIn(final CompleteInterval interval) {
        return interval.stream()
                .filter(this::isBusinessDay)
                .collect(Collectors.toCollection(TreeSet::new));
    }

}
