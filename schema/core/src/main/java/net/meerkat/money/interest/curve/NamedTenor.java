package net.meerkat.money.interest.curve;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlAttribute;

import net.coljate.map.ConcurrentMap;
import net.meerkat.utils.HasName;
import net.ollie.goat.temporal.date.count.ActualActualDateArithmetic;
import net.ollie.goat.temporal.date.count.YearCount;
import net.ollie.goat.temporal.date.years.PeriodYears;
import net.ollie.goat.temporal.date.years.Years;

/**
 *
 * @author ollie
 */
public class NamedTenor implements Tenor, HasName {

    public static final Tenor SPOT = new NamedTenor("SPOT", Period.ZERO);

    private static final ConcurrentMap<String, Tenor> cache = ConcurrentMap.createHashMap();

    public static Tenor ofDays(final int days) {
        return days == 0
                ? SPOT
                : cache.computeIfAbsent(days + "D", n -> new NamedTenor(n, Period.ofDays(days)));
    }

    public static Tenor ofMonths(final int months) {
        if (months % 12 == 0) {
            return ofYears(months / 12);
        }
        return cache.computeIfAbsent(months + "M", n -> new NamedTenor(n, Period.ofMonths(months)));
    }

    public static Tenor ofYears(final int years) {
        return years == 0
                ? SPOT
                : cache.computeIfAbsent(years + "Y", n -> new NamedTenor(n, Period.ofYears(years)));
    }

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "period")
    private Period period;

    @Deprecated
    protected NamedTenor() {
    }

    protected NamedTenor(final String name, final Period period) {
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

    public Optional<Tenor> plus(final Tenor that) {
        return Optional.empty(); //TODO
    }

    @Nonnull
    public Years yearFraction(final LocalDate start) {
        return this.yearFraction(start, ActualActualDateArithmetic.ACT_ACT);
    }

    @Nonnull
    public Years yearFraction(final LocalDate start, final YearCount yearCount) {
        final LocalDate end = start.plus(this.period());
        return yearCount.yearsBetween(start, end);
    }

    public Years years() {
        return new PeriodYears(period);
    }

    @Override
    public BigDecimal decimalValue() {
        return this.years().decimalValue();
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return this.years().decimalValue(context);
    }

    @Override
    public Tenor times(final Number that, final RoundingMode rounding) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
