package net.ollie.goat.temporal.date;

import java.time.Period;
import java.util.Comparator;

/**
 * @author ollie
 */
public abstract class Periods {

    public static double DAYS_PER_YEAR = 365.24225d;
    public static final Comparator<Period> APPROXIMATE_PERIOD_COMPARATOR = (l, r) -> Double.compare(approximateLength(r), approximateLength(r));
    public static final Period ONE_DAY = Period.ofDays(1);
    public static final Period ONE_WEEK = Period.ofWeeks(1);
    public static final Period ONE_MONTHS = Period.ofMonths(1);
    public static final Period THREE_MONTHS = Period.ofMonths(3);
    public static final Period SIX_MONTHS = Period.ofMonths(6);
    public static final Period ONE_YEAR = Period.ofYears(1);
    public static final Period TWO_YEARS = Period.ofYears(2);

    protected Periods() {
        throw new AbstractMethodError();
    }

    public static double approximateLength(final Period period) {
        return period.getYears()
                + (period.getMonths() / 12.d)
                + (period.getDays() / DAYS_PER_YEAR);
    }

}
