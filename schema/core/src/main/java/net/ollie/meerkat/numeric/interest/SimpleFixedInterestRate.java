package net.ollie.meerkat.numeric.interest;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.math3.fraction.Fraction;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.interest.daycount.YearCount;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class SimpleFixedInterestRate implements FixedInterestRate {

    @XmlAttribute(name = "rate")
    private Percentage rate;

    @XmlElementRef(name = "day_count")
    private YearCount yearCount;

    public SimpleFixedInterestRate(final Percentage rate, final YearCount dayCount) {
        this.rate = rate;
        this.yearCount = dayCount;
    }

    @Override
    public String name() {
        return "simple@" + rate;
    }

    @Override
    public Percentage annualRate() {
        return rate;
    }

    @Override
    public YearCount yearCount() {
        return yearCount;
    }

    @Override
    public <C extends CurrencyId> Money<C> accrue(final Money<C> money, final LocalDate start, final LocalDate accrualDate) {
        final Fraction years = yearCount.yearsBetween(start, accrualDate);
        return this.accrue(money, years);
    }

    public <C extends CurrencyId> Money<C> accrue(final Money<C> money, final Fraction years) {
        final double multiplier = 1 + (rate.doubleValue() * years.doubleValue());
        return money.times(multiplier);
    }

    @Override
    public InterestRate with(final Percentage rate) {
        return new SimpleFixedInterestRate(rate, yearCount);
    }

}
