package net.ollie.meerkat.numeric.interest;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.goat.currency.CurrencyId;
import net.ollie.goat.money.Money;
import net.ollie.goat.money.interest.daycount.AccrualFactor;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.date.years.Years;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class SimpleFixedInterestRate extends FixedInterestRate {

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
        final Years years = accrual.yearsBetween(start, accrualDate);
        return this.accrue(money, years);
    }

    public <C extends CurrencyId> Money<C> accrue(final Money<C> money, final Years years) {
        final BigDecimal multiplier = BigDecimal.ONE.add(rate.decimalValue().multiply(years.decimalValue()));
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
