package net.ollie.meerkat.numeric.interest.daycount;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.temporal.ChronoUnit;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

import org.apache.commons.math3.fraction.Fraction;

/**
 *
 * @author Ollie
 */
@XmlEnum
public enum ActualActualAccrualFactor implements AccrualFactor {

    @XmlEnumValue("ACT_ACT")
    ACT_ACT,
    @XmlEnumValue("ACT_ACT_ICMA")
    ACT_ACT_ICMA;

    @Override
    public int daysBetween(final LocalDate startInclusive, final LocalDate endExclusive) {
        return Math.toIntExact(ChronoUnit.DAYS.between(startInclusive, endExclusive));
    }

    private static final MonthDay JAN_1 = MonthDay.of(Month.JANUARY, 1);
    private static final MonthDay DEC_31 = MonthDay.of(Month.DECEMBER, 31);

    @Override
    public Fraction yearsBetween(final LocalDate startInclusive, final LocalDate endExclusive) {
        Fraction fraction = Fraction.ZERO;
        for (int year = startInclusive.getYear(); year <= endExclusive.getYear(); year++) {
            final LocalDate start = latest(JAN_1.atYear(year), startInclusive);
            final LocalDate end = earliest(endExclusive, DEC_31.atYear(year));
            final int periodDaysInYear = this.daysBetween(start, end);
            final int allDaysInYear = Year.isLeap(year) ? 366 : 365;
            fraction = fraction.add(new Fraction(periodDaysInYear, allDaysInYear));
        }
        return fraction;
    }

    private static LocalDate earliest(final LocalDate d1, final LocalDate d2) {
        return d1.isBefore(d2) ? d1 : d2;
    }

    private static LocalDate latest(final LocalDate d1, final LocalDate d2) {
        return d1.isAfter(d2) ? d1 : d2;
    }

}
