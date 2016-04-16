package net.ollie.meerkat.numeric.interest;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.time.daycount.AccrualFactor;
import net.ollie.meerkat.utils.time.Years;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class CompoundFixedInterestRate implements FixedInterestRate {

    @XmlAttribute(name = "annual_rate")
    private Percentage annualRate;

    @XmlElementRef(name = "year_count")
    private AccrualFactor accrual;

    @XmlAttribute(name = "frequency")
    private double yearlyFrequency;

    @Deprecated
    CompoundFixedInterestRate() {
    }

    public CompoundFixedInterestRate(final Percentage annualRate, final AccrualFactor accrual, final double yearlyFrequency) {
        this.annualRate = annualRate;
        this.accrual = accrual;
        this.yearlyFrequency = yearlyFrequency;
    }

    @Override
    public Percentage annualRate() {
        return annualRate;
    }

    @Override
    public AccrualFactor accrual() {
        return accrual;
    }

    @Override
    public <C extends CurrencyId> Money<C> accrue(final Money<C> money, final LocalDate start, final LocalDate accrualDate) {
        final Years years = accrual.yearsBetween(start, accrualDate);
        final double multiplier = Math.pow(1. + annualRate.doubleValue() / yearlyFrequency, yearlyFrequency * years.doubleValue());
        return money.times(multiplier);
    }

    @Override
    public CompoundFixedInterestRate with(final Percentage rate) {
        return new CompoundFixedInterestRate(rate, accrual, yearlyFrequency);
    }

    @Override
    public String toString() {
        return "compound@" + annualRate;
    }

}
