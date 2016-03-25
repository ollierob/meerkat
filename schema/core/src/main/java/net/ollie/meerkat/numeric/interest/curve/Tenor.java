package net.ollie.meerkat.numeric.interest.curve;

import java.time.LocalDate;
import java.time.Period;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAttribute;

import org.apache.commons.math3.fraction.Fraction;

import net.ollie.goat.date.Dates;
import net.ollie.meerkat.numeric.interest.daycount.ActualActualAccrualFactor;
import net.ollie.meerkat.numeric.interest.daycount.YearCount;
import net.ollie.meerkat.utils.HasName;

/**
 *
 * @author Ollie
 */
public class Tenor implements HasName {

    public static final Tenor SPOT = new Tenor("spot", Period.ZERO);

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

    public LocalDate outFrom(final LocalDate date) {
        return date.plus(period);
    }

}
