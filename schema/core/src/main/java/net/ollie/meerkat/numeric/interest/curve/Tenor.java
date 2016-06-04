package net.ollie.meerkat.numeric.interest.curve;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAttribute;

import com.google.common.collect.Maps;

import net.ollie.goat.date.Dates;
import net.ollie.goat.date.years.DoubleYears;
import net.ollie.goat.money.interest.daycount.ActualActualAccrualFactor;
import net.ollie.goat.money.interest.daycount.YearCount;
import net.ollie.meerkat.utils.Classes;
import net.ollie.meerkat.utils.HasName;
import net.ollie.goat.date.years.Years;

/**
 *
 * @author Ollie
 */
public class Tenor implements Years, HasName {

    private static final long serialVersionUID = 1L;

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
    public BigDecimal decimalValue(final MathContext context) {
        return BigDecimal.valueOf(this.doubleValue());
    }

    @Override
    public double doubleValue() {
        return Dates.approximateLength(this.period());
    }

    @Override
    public Years plus(final Years that) {
        return Classes.cast(that, Tenor.class)
                .flatMap(this::plus)
                .map(t -> (Years) t)
                .orElseGet(() -> new DoubleYears(this.doubleValue()).plus(that));
    }

    public Optional<Tenor> plus(final Tenor that) {
        return Optional.empty(); //TODO
    }

    @Override
    public Years times(final Number that, final RoundingMode rounding) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Nonnull
    public Years yearFraction(final LocalDate start) {
        return this.yearFraction(start, ActualActualAccrualFactor.ACT_ACT);
    }

    @Nonnull
    public Years yearFraction(final LocalDate start, final YearCount yearCount) {
        final LocalDate end = start.plus(this.period());
        return yearCount.yearsBetween(start, end);
    }

    @Override
    public LocalDate addTo(final LocalDate date) {
        return date.plus(period);
    }

}
