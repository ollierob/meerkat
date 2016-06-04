package net.ollie.meerkat.numeric.interest;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.goat.currency.Currency;
import net.ollie.goat.date.years.Years;
import net.ollie.goat.money.Money;
import net.ollie.goat.money.interest.daycount.AccrualFactor;
import net.ollie.goat.numeric.percentage.Percentage;

/**
 *
 * @author ollie
 */
public class ContinuousFixedInterestRate extends FixedInterestRate {

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
    public <C extends Currency> Money<C> accrue(final Money<C> money, final LocalDate start, final LocalDate accrualDate) {
        return accrueContinuously(money, annualRate, start, accrualDate, accrual);
    }

    public static <C extends Currency> Money<C> accrueContinuously(final Money<C> amount, final Percentage annualRate, final LocalDate start, final LocalDate accrualDate, final AccrualFactor accrual) {
        final Years years = accrual.yearsBetween(start, accrualDate);
        final double multiplier = Math.exp(annualRate.doubleValue() * years.doubleValue());
        return amount.times(multiplier);
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
