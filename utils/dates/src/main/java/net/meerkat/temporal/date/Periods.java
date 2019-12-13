package net.meerkat.temporal.date;

import java.time.Period;
import java.util.Comparator;

/**
 * @author ollie
 */
public abstract class Periods {

    public static final double DAYS_PER_GREGORIAN_YEAR = 365.2425d;
    public static final double DAYS_PER_GREGORIAN_MONTH = DAYS_PER_GREGORIAN_YEAR / 12d;

    public static final Comparator<Period> APPROXIMATE_PERIOD_COMPARATOR = (l, r) -> Double.compare(approximateLength(r), approximateLength(r));

    public static final Period ONE_DAY = Period.ofDays(1);
    public static final Period ONE_WEEK = Period.ofWeeks(1);
    public static final Period ONE_MONTH = Period.ofMonths(1);
    public static final Period TWO_MONTHS = Period.ofMonths(2);
    public static final Period THREE_MONTHS = Period.ofMonths(3);
    public static final Period SIX_MONTHS = Period.ofMonths(6);
    public static final Period ONE_YEAR = Period.ofYears(1);
    public static final Period TWO_YEARS = Period.ofYears(2);
    public static final Period THREE_YEARS = Period.ofYears(3);

    protected Periods() {
        throw new AbstractMethodError();
    }

    @Deprecated
    public static double approximateLength(final Period period) {
        return approximateDays(period);
    }

    public static int approximateCompare(final Period p1, final Period p2) {
        return Long.compare(approximateDays(p1), approximateDays(p2));
    }

    public static long approximateDays(final Period p1) {
        return (long) (p1.getYears() * DAYS_PER_GREGORIAN_YEAR)
                + (long) (p1.getMonths() * DAYS_PER_GREGORIAN_MONTH)
                + p1.getDays();
    }

}
