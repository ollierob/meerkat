package net.meerkat.time.calendar.holiday;

import java.time.LocalDate;
import java.util.NavigableSet;
import java.util.TreeSet;

import javax.annotation.Nonnull;

import net.meerkat.time.calendar.Calendar;
import net.ollie.goat.temporal.date.interim.CompleteInterval;

/**
 *
 * @author Ollie
 */
public interface HolidayCalendar extends Calendar {

    /**
     *
     * @param date
     * @return
     * @throws IllegalArgumentException
     */
    boolean isHoliday(@Nonnull LocalDate date) throws UnsupportedDateException;

    /**
     *
     * @param date
     * @param n
     * @return the holiday date {@code n} days after (or before, if negative) the given date. If {@code n == 0} the
     * given date is returned even if it's not a holiday.
     * @throws IllegalArgumentException if a date before the nth holiday is
     * {@link #isSupported(java.time.LocalDate) not supported}.
     */
    default LocalDate nthHoliday(final LocalDate date, final int n) {
        final int signum = n > 0 ? +1 : -1;
        final int m = Math.abs(n);
        int c = 0;
        LocalDate current = date;
        while (c < m) {
            current = current.plusDays(signum);
            if (this.isHoliday(current)) {
                c++;
            } else if (!this.isSupported(date)) {
                throw new UnsupportedDateException(date);
            }
        }
        return current;
    }

    /**
     *
     * @param date
     * @return the next business date strictly after the given date.
     */
    default LocalDate nextHoliday(final LocalDate date) {
        return this.nthHoliday(date, 1);
    }

    /**
     *
     * @param date
     * @return the previous business date strictly before the given date.
     */
    default LocalDate previousHoliday(final LocalDate date) {
        return this.nthHoliday(date, -1);
    }

    @Nonnull
    default NavigableSet<LocalDate> holidaysIn(final LocalDate startInclusive, final LocalDate endExclusive) throws UnsupportedDateException {
        final NavigableSet<LocalDate> holidays = new TreeSet<>();
        LocalDate date = startInclusive;
        while (date.isBefore(endExclusive)) {
            if (this.isHoliday(date)) {
                holidays.add(date);
            } else if (!this.isSupported(date)) {
                throw new UnsupportedDateException(date);
            }
            date = date.plusDays(1);
        }
        return holidays;
    }

    @Nonnull
    default NavigableSet<LocalDate> holidaysIn(final CompleteInterval interval) {
        return this.holidaysIn(interval.startInclusive(), interval.endExclusive());
    }

}
