package net.meerkat.money.interest.accrual;

import java.math.BigDecimal;

import net.meerkat.money.Money;
import net.meerkat.money.currency.Currency;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.years.Years;

/**
 *
 * @author Ollie
 */
public class SimpleInterestAccrual implements InterestAccrual {

    public static final InterestAccrual INSTANCE = new SimpleInterestAccrual();

    SimpleInterestAccrual() {
    }

    @Override
    public <C extends Currency> Money<C> accrue(final Money<C> money, final Percentage annualRate, final Years term) {
        final BigDecimal multiplier = BigDecimal.ONE.add(term.times(annualRate).decimalValue());
        return money.times(multiplier);
    }

}
