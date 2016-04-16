package net.ollie.meerkat.numeric.interest;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.Percentage;
import net.ollie.meerkat.numeric.money.Money;
import net.ollie.meerkat.time.daycount.AccrualFactor;
import net.ollie.meerkat.utils.time.Years;

/**
 *
 * @author ollie
 */
public class ContinuousFixedInterestRate implements FixedInterestRate {

    @XmlAttribute(name = "rate")
    private Percentage annualRate;

    @XmlElementRef(name = "accrual")
    private AccrualFactor accrual;

    @Deprecated
    ContinuousFixedInterestRate() {
    }

    public ContinuousFixedInterestRate(final Percentage rate, final AccrualFactor accrual) {
        this.annualRate = rate;
        this.accrual = accrual;
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
        final double multiplier = Math.exp(annualRate.doubleValue() * years.doubleValue());
        return money.times(multiplier);
    }

    @Override
    public ContinuousFixedInterestRate with(final Percentage rate) {
        return new ContinuousFixedInterestRate(rate, accrual);
    }

    @Override
    public String toString() {
        return "continuous@" + annualRate;
    }

}
