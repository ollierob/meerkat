package net.meerkat.money.interest.fixed;

import net.meerkat.money.Money;
import net.meerkat.money.currency.Currency;
import net.meerkat.money.interest.accrual.InterestAccrual;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.count.DateArithmetic;
import net.ollie.goat.temporal.date.years.Years;

/**
 *
 * @author ollie
 */
public class ContinuousFixedInterestRate extends FixedInterestRate {

    @Deprecated
    ContinuousFixedInterestRate() {
    }

    public ContinuousFixedInterestRate(final Percentage rate, final DateArithmetic accrual) {
        super(rate, accrual);
    }

    @Override
    public <C extends Currency> Money<C> accrue(final Money<C> money, final Years years) {
        return InterestAccrual.continuous().accrue(money, this.annualRate(), years);
    }

    @Override
    public ContinuousFixedInterestRate with(final Percentage rate) {
        return new ContinuousFixedInterestRate(rate, this.dateArithmetic());
    }

    @Override
    public String toString() {
        return "continuous@" + this.annualRate();
    }

}
