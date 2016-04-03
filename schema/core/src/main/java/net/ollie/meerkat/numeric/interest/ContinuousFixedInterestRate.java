package net.ollie.meerkat.numeric.interest;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;

import org.apache.commons.math3.fraction.Fraction;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.interest.daycount.YearCount;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author ollie
 */
public class ContinuousFixedInterestRate implements FixedInterestRate {

    @XmlAttribute(name = "rate")
    private Percentage annualRate;

    @XmlElementRef(name = "year_count")
    private YearCount yearCount;

    @Deprecated
    ContinuousFixedInterestRate() {
    }

    public ContinuousFixedInterestRate(final Percentage rate, final YearCount yearCount) {
        this.annualRate = rate;
        this.yearCount = yearCount;
    }

    @Override
    public Percentage annualRate() {
        return annualRate;
    }

    @Override
    public YearCount yearCount() {
        return yearCount;
    }

    @Override
    public <C extends CurrencyId> Money<C> accrue(final Money<C> money, final LocalDate start, final LocalDate accrualDate) {
        final Fraction years = yearCount.yearsBetween(start, accrualDate);
        final double multiplier = Math.exp(annualRate.doubleValue() * years.doubleValue());
        return money.times(multiplier);
    }

    @Override
    public ContinuousFixedInterestRate with(final Percentage rate) {
        return new ContinuousFixedInterestRate(rate, yearCount);
    }

    @Override
    public String toString() {
        return "continuous@" + annualRate;
    }

}
