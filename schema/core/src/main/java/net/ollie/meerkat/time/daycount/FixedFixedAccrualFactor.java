package net.ollie.meerkat.time.daycount;

import java.time.LocalDate;
import java.time.Month;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

import net.ollie.meerkat.time.FractionalYears;
import net.ollie.meerkat.utils.time.Years;

/**
 *
 * @author ollie
 */
@XmlEnum
public enum FixedFixedAccrualFactor implements AccrualFactor {

    @XmlEnumValue("30_360")
    THIRTY_THREESIXTY(30, 360) {

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
        AdjustedDate adjustStart(LocalDate start, LocalDate end) {
            return this.adjust(end);
        }

        @Override
        AdjustedDate adjustEnd(final LocalDate start, final LocalDate end) {
            return this.adjust(start);
        }

        private AdjustedDate adjust(final LocalDate date) {
            return date.getDayOfMonth() == 31
                    ? new AdjustedDate(date, 30)
                    : new AdjustedDate(date);
        }

    },
    @XmlEnumValue("30_360_ISDA")
    THIRTY_THREESIXTY_ISDA(30, 360) {

        @Override
        AdjustedDate adjustStart(final LocalDate start, final LocalDate end) {
            return isEndOfMonth(start)
                    ? new AdjustedDate(start, 30)
                    : new AdjustedDate(start);
        }

        @Override
        AdjustedDate adjustEnd(final LocalDate start, final LocalDate end) {
            return isEndOfMonth(end) && end.getMonth() != Month.FEBRUARY
                    ? new AdjustedDate(end, 30)
                    : new AdjustedDate(end);
        }

    };

    private final int daysPerMonth;
    private final int daysPerYear;

    private FixedFixedAccrualFactor(final int daysPerMonth, final int daysPerYear) {
        this.daysPerMonth = daysPerMonth;
        this.daysPerYear = daysPerYear;
    }

    @Override
    public int daysBetween(LocalDate startInclusive, LocalDate endExclusive) {
        return this.daysBetween(
                this.adjustStart(startInclusive, endExclusive),
                this.adjustEnd(startInclusive, endExclusive));
    }

    AdjustedDate adjustStart(final LocalDate start, final LocalDate end) {
        return new AdjustedDate(start);
    }

    AdjustedDate adjustEnd(final LocalDate start, final LocalDate end) {
        return new AdjustedDate(end);
    }

    int daysBetween(final AdjustedDate start, final AdjustedDate end) {
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

    static class AdjustedDate {

        private final LocalDate date;
        private final int dayOfMonth;

        AdjustedDate(final LocalDate date) {
            this(date, date.getDayOfMonth());
        }

        AdjustedDate(final LocalDate date, final int dayOfMonth) {
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
