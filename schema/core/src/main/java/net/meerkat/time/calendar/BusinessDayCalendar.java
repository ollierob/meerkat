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

    boolean isBusinessDay(@Nonnull LocalDate date);

    default boolean isHoliday(@Nonnull final LocalDate date) {
        return !this.isBusinessDay(date);
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

    /**
     *
     * @param date
     * @param n
     * @return the business date {@code n} days after (or before, if negative) the given date. If {@code n == 0} the
     * given date is returned, even if it is not a business day.
     */
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

    /**
     *
     * @param date
     * @return the next business date strictly after the given date.
     */
    default LocalDate nextBusinessDay(final LocalDate date) {
        return this.nthBusinessDay(date, 1);
    }

    /**
     *
     * @param date
     * @return the previous business date strictly before the given date.
     */
    default LocalDate previousBusinessDay(final LocalDate date) {
        return this.nthBusinessDay(date, -1);
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
