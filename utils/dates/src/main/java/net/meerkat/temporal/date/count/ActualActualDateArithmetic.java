package net.meerkat.temporal.date.count;

import net.meerkat.temporal.date.years.FractionalYears;
import net.meerkat.temporal.date.years.Years;
import org.apache.commons.math3.fraction.Fraction;

import java.time.*;

/**
 * @author Ollie
 */
public enum ActualActualDateArithmetic implements DateArithmetic, ActualDayCount {

    ACT_ACT_ICMA {
        @Override
        public Years yearsBetween(final LocalDate startInclusive, final LocalDate endInclusive) {
            return Years.between(startInclusive, endInclusive);
        }
    },
    ACT_ACT_ISDA {
        @Override
        public Years yearsBetween(final LocalDate startInclusive, final LocalDate endExclusive) {
            Fraction fraction = Fraction.ZERO;
            for (int year = startInclusive.getYear(); year <= endExclusive.getYear(); year++) {
                final LocalDate start = latest(JAN_1.atYear(year), startInclusive);
                final LocalDate end = earliest(endExclusive, DEC_31.atYear(year));
                final int periodDaysInYear = this.daysBetween(start, end);
                final int allDaysInYear = Year.isLeap(year) ? 366 : 365;
                fraction = fraction.add(new Fraction(periodDaysInYear, allDaysInYear));
            }
            return new FractionalYears(fraction);
        }
    };

    private static final MonthDay JAN_1 = MonthDay.of(Month.JANUARY, 1);
    private static final MonthDay DEC_31 = MonthDay.of(Month.DECEMBER, 31);

    @Override
    public Period between(final LocalDate start, final LocalDate end) {
        return Period.between(start, end);
    }

    private static LocalDate earliest(final LocalDate d1, final LocalDate d2) {
        return d1.isBefore(d2) ? d1 : d2;
    }

    private static LocalDate latest(final LocalDate d1, final LocalDate d2) {
        return d1.isAfter(d2) ? d1 : d2;
    }

}
