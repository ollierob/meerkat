package net.ollie.meerkat.time.calendar;

import java.time.LocalDate;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import net.ollie.meerkat.time.interim.Interval;

/**
 *
 * @author Ollie
 */
public interface BusinessCalendar {

    boolean isHoliday(@Nonnull LocalDate date);

    default boolean isNotHoliday(@Nonnull final LocalDate date) {
        return !this.isHoliday(date);
    }

    @Nonnull
    default NavigableSet<LocalDate> holidaysIn(final LocalDate startInclusive, final LocalDate endExclusive) {
        final NavigableSet<LocalDate> holidays = new TreeSet<>();
        LocalDate date = startInclusive;
        while (date.isBefore(endExclusive)) {
            if (!this.isNotHoliday(date)) {
                holidays.add(date);
            }
        }
        return holidays;
    }

    @Nonnull
    default NavigableSet<LocalDate> holidaysIn(final Interval interval) {
        return interval.stream()
                .filter(this::isNotHoliday)
                .collect(Collectors.toCollection(TreeSet::new));
    }

}
