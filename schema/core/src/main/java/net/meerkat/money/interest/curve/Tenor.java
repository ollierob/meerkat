package net.meerkat.money.interest.curve;

import net.meerkat.Explainable;
import net.meerkat.temporal.date.Periods;
import net.meerkat.temporal.date.count.DayCount;
import net.meerkat.temporal.date.years.Years;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.time.Period;

/**
 * A normalized {@link Period}.
 *
 * @author ollie
 */
public class Tenor implements Explainable, Comparable<Tenor> {

    public static final Tenor SPOT = new Tenor(Period.ZERO);
    public static final Tenor ONE_DAY = new Tenor(Period.ofDays(1));
    public static final Tenor ONE_MONTH = new Tenor(Period.ofMonths(1));
    public static final Tenor THREE_MONTHS = new Tenor(Period.ofMonths(3));
    public static final Tenor SIX_MONTHS = new Tenor(Period.ofMonths(6));
    public static final Tenor ONE_YEAR = new Tenor(Period.ofYears(1));

    private final Period period;

    public Tenor(@Nonnull final Period period) {
        this.period = period.normalized();
    }

    public Period period() {
        return period;
    }

    @Override
    public ExplanationBuilder explain() {
        return this.explanationBuilder()
                .put("period", period);
    }

    public Years toYears() {
        return Years.of(period);
    }

    @Override
    public int compareTo(final Tenor that) {
        return Periods.APPROXIMATE_PERIOD_COMPARATOR.compare(this.period, that.period);
    }

    public int numDays(final DayCount dayCount, final LocalDate start) {
        return dayCount.daysIn(period, start);
    }

    public String toTenorString() {
        final String periodString = period.toString();
        return periodString.substring(1, periodString.length()); //Drop P
    }

}
