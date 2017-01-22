package net.ollie.meerkat.money.interest.fixed;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.money.Money;
import net.ollie.meerkat.money.currency.Currency;
import net.ollie.meerkat.money.interest.accrual.InterestAccrual;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.count.DateArithmetic;
import net.ollie.goat.temporal.date.years.Years;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class CompoundFixedInterestRate extends FixedInterestRate {

    @XmlAttribute(name = "frequency")
    private double yearlyFrequency;

    @Deprecated
    CompoundFixedInterestRate() {
    }

    public CompoundFixedInterestRate(final Percentage annualRate, final DateArithmetic accrual, final double yearlyFrequency) {
        super(annualRate, accrual);
        this.yearlyFrequency = yearlyFrequency;
    }

    @Override
    public <C extends Currency> Money<C> accrue(final Money<C> money, final Years years) {
        return InterestAccrual.compound(yearlyFrequency).accrue(money, this.annualRate(), years);
    }

    @Override
    public CompoundFixedInterestRate with(final Percentage rate) {
        return new CompoundFixedInterestRate(rate, this.dateArithmetic(), yearlyFrequency);
    }

    @Override
    public String toString() {
        return "compound@" + this.annualRate();
    }

}
