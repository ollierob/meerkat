package net.meerkat.money.interest.curve;

import java.time.LocalDate;
import java.time.Period;

import javax.annotation.Nonnull;

import net.meerkat.Explainable;
import net.ollie.goat.temporal.date.Periods;
import net.ollie.goat.temporal.date.count.DayCount;
import net.ollie.goat.temporal.date.years.Years;

/**
 * A normalized {@link Period}.
 *
 * @author ollie
 */
public class Tenor implements Explainable, Comparable<Tenor> {

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

    public Years years() {
        return Years.of(period);
    }

    @Override
    public int compareTo(final Tenor that) {
        return Periods.APPROXIMATE_PERIOD_COMPARATOR.compare(this.period, that.period);
    }

    public int numDays(final DayCount dayCount, final LocalDate start) {
        return dayCount.daysIn(period, start);
    }

}
