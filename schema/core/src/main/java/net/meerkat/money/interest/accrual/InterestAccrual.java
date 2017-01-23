package net.meerkat.money.interest.accrual;

import net.meerkat.money.Money;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.years.Years;
import net.meerkat.money.currency.CurrencyId;

/**
 *
 * @author Ollie
 */
public interface InterestAccrual {

    <C extends CurrencyId> Money<C> accrue(Money<C> money, Percentage annualRate, Years term);

    static InterestAccrual simple() {
        return SimpleInterestAccrual.INSTANCE;
    }

    static InterestAccrual compound(final double yearlyFrequency) {
        return new CompoundInterestAccrual(yearlyFrequency);
    }

    static InterestAccrual continuous() {
        return ContinuousInterestAccrual.INSTANCE;
    }

}
