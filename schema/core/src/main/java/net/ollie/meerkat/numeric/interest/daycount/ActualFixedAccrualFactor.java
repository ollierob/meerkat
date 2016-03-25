package net.ollie.meerkat.numeric.interest.daycount;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

import org.apache.commons.math3.fraction.Fraction;

/**
 *
 * @author Ollie
 */
@XmlEnum
public enum ActualFixedAccrualFactor implements AccrualFactor {

    @XmlEnumValue("ACT_360")
    ACT_360(360),
    @XmlEnumValue("ACT_364")
    ACT_364(364),
    @XmlEnumValue("ACT_365")
    ACT_365(365);

    private final int daysPerYear;

    private ActualFixedAccrualFactor(int daysPerYear) {
        this.daysPerYear = daysPerYear;
    }

    @Override
    public int daysBetween(final LocalDate startInclusive, final LocalDate endExclusive) {
        return Math.toIntExact(ChronoUnit.DAYS.between(startInclusive, endExclusive));
    }

    @Override
    public Fraction yearsBetween(final LocalDate startInclusive, final LocalDate endExclusive) {
        return new Fraction(this.daysBetween(startInclusive, endExclusive), daysPerYear);
    }

}
