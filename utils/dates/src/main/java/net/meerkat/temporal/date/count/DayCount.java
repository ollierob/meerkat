package net.meerkat.temporal.date.count;

import net.meerkat.temporal.date.interim.CompleteInterval;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.Period;

/**
 * @author Ollie
 */
public interface DayCount {

    int daysBetween(@Nonnull LocalDate startInclusive, @Nonnull LocalDate endInclusive);

    default int daysIn(@Nonnull final CompleteInterval interim) {
        return this.daysBetween(interim.startInclusive(), interim.endExclusive());
    }

    default int daysIn(final Period tenor, final LocalDate start) {
        return this.daysBetween(start, start.plus(tenor));
    }

    ActualDayCount ACTUAL = ActualActualDateArithmetic.ACT_ACT;

}
