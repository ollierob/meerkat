package net.meerkat.temporal.date.count;

import java.time.LocalDate;
import java.time.Period;

/**
 * @author Ollie
 */
public interface DateArithmetic extends DayCount, YearCount {

    default Period between(final LocalDate startInclusive, final LocalDate endExclusive) {
        return Period.ofDays(this.daysBetween(startInclusive, endExclusive));
    }

    DateArithmetic ACT_ACT = ActualActualDateArithmetic.ACT_ACT;
    DateArithmetic ACT_360 = ActualFixedDateArithmetic.ACT_360;
    DateArithmetic ACT_365 = ActualFixedDateArithmetic.ACT_365;
    DateArithmetic THIRTY_360 = FixedFixedDateArithmetic.THIRTY_360;
    DateArithmetic THIRTY_365 = FixedFixedDateArithmetic.THIRTY_365;

    static DateArithmetic of(final DayCount dayCount, final YearCount yearCount) {
        return new MixedDateArithmetic(dayCount, yearCount);
    }

}
