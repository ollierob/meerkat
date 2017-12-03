package net.meerkat.money.interest.fixed;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.numeric.percentage.Percentage;
import net.meerkat.temporal.date.count.DateArithmetic;
import net.meerkat.temporal.date.years.Years;

/**
 *
 * @author ollie
 */
public class CompoundFixedInterestRate extends FixedInterestRate {

    private final double yearlyFrequency;

    public CompoundFixedInterestRate(
            final Percentage annualRate,
            final DateArithmetic accrual,
            final double yearlyFrequency) {
        super(annualRate, accrual);
        this.yearlyFrequency = yearlyFrequency;
    }

    @Override
    public <C extends CurrencyId> Money<C> accrue(final Money<C> money, final Years years) {
        return accrue(money, this.annualRate(), yearlyFrequency, years);
    }

    @Override
    public CompoundFixedInterestRate with(final Percentage rate) {
        return new CompoundFixedInterestRate(rate, this.dateArithmetic(), yearlyFrequency);
    }

    @Override
    protected String type() {
        return "compound";
    }

    public static <C extends CurrencyId> Money<C> accrue(
            final Money<C> money,
            final Percentage annualRate,
            final double yearlyFrequency,
            final Years term) {
        final double multiplier = Math.pow(1. + annualRate.doubleValue() / yearlyFrequency, yearlyFrequency * term.doubleValue());
        return money.times(multiplier);

    }

}
