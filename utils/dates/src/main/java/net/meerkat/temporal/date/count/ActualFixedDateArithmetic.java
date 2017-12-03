package net.meerkat.temporal.date.count;

import net.meerkat.temporal.date.years.FractionalYears;
import net.meerkat.temporal.date.years.Years;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import java.time.LocalDate;
import java.time.Period;

/**
 * @author Ollie
 */
@XmlEnum
public enum ActualFixedDateArithmetic implements DateArithmetic, ActualDayCount {

    @XmlEnumValue("ACT_360")
    ACT_360(360),
    @XmlEnumValue("ACT_364")
    ACT_364(364),
    @XmlEnumValue("ACT_365")
    ACT_365(365);

    private final int daysPerYear;

    private ActualFixedDateArithmetic(int daysPerYear) {
        this.daysPerYear = daysPerYear;
    }

    @Override
    public Period between(final LocalDate startInclusive, final LocalDate endExclusive) {
        final int days = this.daysBetween(startInclusive, endExclusive);
        return Period.of(days % daysPerYear, 0, days / daysPerYear);
    }

    @Override
    public Years yearsBetween(final LocalDate startInclusive, final LocalDate endExclusive) {
        return FractionalYears.of(this.daysBetween(startInclusive, endExclusive), daysPerYear);
    }

}
