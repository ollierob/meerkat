package net.ollie.meerkat.numeric.interest;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.time.daycount.AccrualFactor;
import net.ollie.meerkat.numeric.money.Money;

import org.apache.commons.math3.fraction.Fraction;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class SimpleFixedInterestRate implements FixedInterestRate {

    @XmlAttribute(name = "rate")
    private Percentage rate;

    @XmlElementRef(name = "accrual")
    private AccrualFactor accrual;

    @Deprecated
    SimpleFixedInterestRate() {
    }

    public SimpleFixedInterestRate(final Percentage rate, final AccrualFactor accrual) {
        this.rate = rate;
        this.accrual = accrual;
    }

    @Override
    public Percentage annualRate() {
        return rate;
    }

    @Override
    public AccrualFactor accrual() {
        return accrual;
    }

    @Override
    public <C extends CurrencyId> Money<C> accrue(final Money<C> money, final LocalDate start, final LocalDate accrualDate) {
        final Fraction years = accrual.yearsBetween(start, accrualDate);
        return this.accrue(money, years);
    }

    public <C extends CurrencyId> Money<C> accrue(final Money<C> money, final Fraction years) {
        final double multiplier = 1 + (rate.doubleValue() * years.doubleValue());
        return money.times(multiplier);
    }

    @Override
    public SimpleFixedInterestRate with(final Percentage rate) {
        return new SimpleFixedInterestRate(rate, accrual);
    }

    @Override
    public String toString() {
        return "simple@" + rate;
    }

}
