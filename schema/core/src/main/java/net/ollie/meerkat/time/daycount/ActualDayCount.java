package net.ollie.meerkat.time.daycount;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author ollie
 */
public interface ActualDayCount extends DayCount {

    @Override
    public default int daysBetween(LocalDate startInclusive, LocalDate endExclusive) {
        return Math.toIntExact(ChronoUnit.DAYS.between(startInclusive, endExclusive));
    }

}
