package net.meerkat.money.interest.fixed;

import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.money.Money;
import net.meerkat.money.interest.accrual.InterestAccrual;
import net.ollie.goat.numeric.percentage.Percentage;
import net.ollie.goat.temporal.date.count.DateArithmetic;
import net.ollie.goat.temporal.date.years.Years;
import net.meerkat.money.currency.CurrencyId;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class SimpleFixedInterestRate extends FixedInterestRate {

    @Deprecated
    SimpleFixedInterestRate() {
    }

    public SimpleFixedInterestRate(final Percentage rate, final DateArithmetic accrual) {
        super(rate, accrual);
    }

    @Override
    public SimpleFixedInterestRate with(final Percentage rate) {
        return new SimpleFixedInterestRate(rate, this.dateArithmetic());
    }

    @Override
    public <C extends CurrencyId> Money<C> accrue(final Money<C> money, final Years years) {
        return InterestAccrual.simple().accrue(money, this.annualRate(), years);
    }

    @Override
    public String toString() {
        return "simple@" + this.annualRate();
    }

}
