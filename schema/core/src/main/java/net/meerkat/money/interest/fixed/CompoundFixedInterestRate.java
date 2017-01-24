package net.meerkat.money.interest.fixed;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.money.Money;
import net.meerkat.money.interest.accrual.InterestAccrual;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.count.DateArithmetic;
import net.ollie.goat.temporal.date.years.Years;
import net.meerkat.identifier.currency.CurrencyId;

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
    public <C extends CurrencyId> Money<C> accrue(final Money<C> money, final Years years) {
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
