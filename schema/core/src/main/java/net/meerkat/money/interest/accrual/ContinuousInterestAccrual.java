package net.meerkat.money.interest.accrual;

import net.meerkat.money.Money;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.years.Years;
import net.meerkat.identifier.currency.CurrencyId;

/**
 *
 * @author Ollie
 */
public class ContinuousInterestAccrual implements InterestAccrual {

    static final InterestAccrual INSTANCE = new ContinuousInterestAccrual();

    ContinuousInterestAccrual() {
    }

    @Override
    public <C extends CurrencyId> Money<C> accrue(final Money<C> money, final Percentage annualRate, final Years term) {
        final double multiplier = Math.exp(annualRate.doubleValue() * term.doubleValue());
        return money.times(multiplier);
    }

}
