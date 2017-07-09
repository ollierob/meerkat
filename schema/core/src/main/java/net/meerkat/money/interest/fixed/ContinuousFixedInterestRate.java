package net.meerkat.money.interest.fixed;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.count.DateArithmetic;
import net.ollie.goat.temporal.date.years.Years;

/**
 *
 * @author ollie
 */
public class ContinuousFixedInterestRate extends FixedInterestRate {

    public ContinuousFixedInterestRate(
            final Percentage rate,
            final DateArithmetic accrual) {
        super(rate, accrual);
    }

    @Override
    public <C extends CurrencyId> Money<C> accrue(final Money<C> money, final Years years) {
        return accrue(money, this.annualRate(), years);
    }

    @Override
    public ContinuousFixedInterestRate with(final Percentage rate) {
        return new ContinuousFixedInterestRate(rate, this.dateArithmetic());
    }

    @Override
    protected String type() {
        return "continuous";
    }

    public static <C extends CurrencyId> Money<C> accrue(
            final Money<C> money,
            final Percentage annualRate,
            final Years term) {
        final double multiplier = Math.exp(annualRate.doubleValue() * term.doubleValue());
        return money.times(multiplier);
    }

}
