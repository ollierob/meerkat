package net.meerkat.temporal.date.count;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * @author ollie
 */
public interface ActualDayCount extends DayCount {

    @Override
    default int daysBetween(LocalDate startInclusive, LocalDate endExclusive) {
        return Math.toIntExact(ChronoUnit.DAYS.between(startInclusive, endExclusive));
    }

}
