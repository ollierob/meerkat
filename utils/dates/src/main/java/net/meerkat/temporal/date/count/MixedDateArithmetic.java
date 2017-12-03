package net.meerkat.temporal.date.count;

import net.meerkat.temporal.date.years.Years;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

/**
 * @author ollie
 */
@XmlRootElement
public class MixedDateArithmetic implements DateArithmetic {

    @XmlElementRef(name = "day_count")
    private DayCount dayCount;

    @XmlElementRef(name = "year_count")
    private YearCount yearCount;

    @Deprecated
    MixedDateArithmetic() {
    }

    public MixedDateArithmetic(final DayCount dayCount, final YearCount yearCount) {
        this.dayCount = dayCount;
        this.yearCount = yearCount;
    }

    @Override
    public Period between(final LocalDate startInclusive, final LocalDate endExclusive) {
        final int days = dayCount.daysBetween(startInclusive, endExclusive);
        final int years = yearCount.yearsBetween(startInclusive, endExclusive).round(RoundingMode.DOWN);
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int daysBetween(LocalDate startInclusive, LocalDate endExclusive) {
        return dayCount.daysBetween(startInclusive, endExclusive);
    }

    @Override
    public Years yearsBetween(LocalDate startInclusive, LocalDate endExclusive) {
        return yearCount.yearsBetween(startInclusive, endExclusive);
    }

}
