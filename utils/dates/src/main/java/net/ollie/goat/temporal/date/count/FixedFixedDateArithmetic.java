package net.ollie.goat.temporal.date.count;

import net.ollie.goat.temporal.date.years.FractionalYears;
import net.ollie.goat.temporal.date.years.Years;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

/**
 * @author ollie
 */
@XmlEnum
public enum FixedFixedDateArithmetic implements DateArithmetic {

    @XmlEnumValue("30_360")
    THIRTY_360(30, 360) {
        @Override
        public int daysBetween(final LocalDate startInclusive, final LocalDate endExclusive) {
            return this.daysBetween(
                    startInclusive.getYear(), endExclusive.getYear(),
                    startInclusive.getMonthValue(), endExclusive.getMonthValue(),
                    startInclusive.getDayOfMonth(), endExclusive.getDayOfMonth());
        }

    },
    @XmlEnumValue("30_360_ICMA")
    THIRTY_THREESIXTY_ICMA(30, 360) {
        @Override
        AdjustedDayOfMonth adjustStart(LocalDate start, LocalDate end) {
            return this.adjust(end);
        }

        @Override
        AdjustedDayOfMonth adjustEnd(final LocalDate start, final LocalDate end) {
            return this.adjust(start);
        }

        private AdjustedDayOfMonth adjust(final LocalDate date) {
            return date.getDayOfMonth() == 31
                    ? new AdjustedDayOfMonth(date, 30)
                    : new AdjustedDayOfMonth(date);
        }

    },
    @XmlEnumValue("30_360_ISDA")
    THIRTY_THREESIXTY_ISDA(30, 360) {
        @Override
        AdjustedDayOfMonth adjustStart(final LocalDate start, final LocalDate end) {
            return isEndOfMonth(start)
                    ? new AdjustedDayOfMonth(start, 30)
                    : new AdjustedDayOfMonth(start);
        }

        @Override
        AdjustedDayOfMonth adjustEnd(final LocalDate start, final LocalDate end) {
            return isEndOfMonth(end) && end.getMonth() != Month.FEBRUARY
                    ? new AdjustedDayOfMonth(end, 30)
                    : new AdjustedDayOfMonth(end);
        }

    };

    private final int daysPerMonth;
    private final int daysPerYear;

    private FixedFixedDateArithmetic(final int daysPerMonth, final int daysPerYear) {
        this.daysPerMonth = daysPerMonth;
        this.daysPerYear = daysPerYear;
    }

    @Override
    public Period between(final LocalDate startInclusive, final LocalDate endExclusive) {
        final int days = this.daysBetween(startInclusive, endExclusive);
        final int months = days / daysPerMonth;
        final int years = days / daysPerYear;
        return Period.of(days % daysPerYear, months % 12, years);
    }

    @Override
    public int daysBetween(final LocalDate startInclusive, final LocalDate endInclusive) {
        return this.daysBetween(
                this.adjustStart(startInclusive, endInclusive),
                this.adjustEnd(startInclusive, endInclusive));
    }

    AdjustedDayOfMonth adjustStart(final LocalDate start, final LocalDate end) {
        return new AdjustedDayOfMonth(start);
    }

    AdjustedDayOfMonth adjustEnd(final LocalDate start, final LocalDate end) {
        return new AdjustedDayOfMonth(end);
    }

    int daysBetween(final AdjustedDayOfMonth start, final AdjustedDayOfMonth end) {
        return this.daysBetween(start.year(), end.year(), start.month(), end.month(), start.day(), end.day());
    }

    int daysBetween(final int y1, final int y2, final int m1, final int m2, final int d1, final int d2) {
        return daysPerYear * (y2 - y1)
                + daysPerMonth * (m2 - m1)
                + (d2 - d1);
    }

    @Override
    public Years yearsBetween(final LocalDate startInclusive, final LocalDate endExclusive) {
        return FractionalYears.of(this.daysBetween(startInclusive, endExclusive), daysPerYear);
    }

    static boolean isEndOfMonth(final LocalDate date) {
        return date.getDayOfMonth() >= 28
                && date.getMonth() != date.plusDays(1).getMonth();
    }

    private static class AdjustedDayOfMonth {

        private final LocalDate date;
        private final int dayOfMonth;

        AdjustedDayOfMonth(final LocalDate date) {
            this(date, date.getDayOfMonth());
        }

        AdjustedDayOfMonth(final LocalDate date, final int dayOfMonth) {
            this.date = date;
            this.dayOfMonth = dayOfMonth;
        }

        int year() {
            return date.getYear();
        }

        int month() {
            return date.getMonthValue();
        }

        int day() {
            return dayOfMonth;
        }

    }

}
