package net.ollie.meerkat.numeric.interest.curve;

import com.google.common.collect.Maps;

import java.time.LocalDate;
import java.time.Period;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAttribute;

import net.ollie.goat.date.Dates;
import net.ollie.meerkat.time.Years;
import net.ollie.meerkat.time.daycount.ActualActualAccrualFactor;
import net.ollie.meerkat.time.daycount.YearCount;
import net.ollie.meerkat.utils.HasName;

import org.apache.commons.math3.fraction.Fraction;

/**
 *
 * @author Ollie
 */
public class Tenor implements Years, HasName {

    public static final Tenor SPOT = new Tenor("SPOT", Period.ZERO);

    private static final Map<String, Tenor> cache = Maps.newConcurrentMap();

    public static Tenor ofMonths(final int months) {
        if (months % 12 == 0) {
            return ofYears(months / 12);
        }
        return cache.computeIfAbsent(months + "M", n -> new Tenor(n, Period.ofMonths(months)));
    }

    public static Tenor ofYears(final int years) {
        return years == 0
                ? SPOT
                : cache.computeIfAbsent(years + "Y", n -> new Tenor(n, Period.ofYears(years)));
    }

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "period")
    private Period period;

    @Deprecated
    Tenor() {
    }

    Tenor(final String name, final Period period) {
        this.name = name;
        this.period = period;
    }

    @Override
    public String name() {
        return name;
    }

    @Nonnull
    public Period period() {
        return period;
    }

    @Override
    public double value() {
        return this.yearFraction();
    }

    @Nonnull
    public double yearFraction() {
        return Dates.approximateLength(this.period());
    }

    @Nonnull
    public Fraction yearFraction(final LocalDate start) {
        return this.yearFraction(start, ActualActualAccrualFactor.ACT_ACT);
    }

    @Nonnull
    public Fraction yearFraction(final LocalDate start, final YearCount yearCount) {
        final LocalDate end = start.plus(this.period());
        return yearCount.yearsBetween(start, end);
    }

    @Override
    public LocalDate addTo(final LocalDate date) {
        return date.plus(period);
    }

}
