package net.meerkat.money.interest.fixed;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.cash.CashPayment;
import net.meerkat.money.Money;
import net.meerkat.numeric.fraction.BigDecimalFraction;
import net.meerkat.numeric.percentage.Percentage;
import net.meerkat.temporal.date.count.DateArithmetic;
import net.meerkat.temporal.date.years.Years;

import java.math.BigDecimal;

/**
 * @author ollie
 */
public class SimpleFixedInterestRate extends FixedInterestRate {

    public static final SimpleFixedInterestRate ZERO_ACT_ACT = new SimpleFixedInterestRate(Percentage.zero(), DateArithmetic.ACT_ACT);

    public static <C extends CurrencyId> SimpleFixedInterestRate implied(
            final CashPayment<C> start,
            final CashPayment<C> end,
            final DateArithmetic arithmetic) {
        //c1 * 1+(i/100) * y = c2 => i = 100*[c2/(c1*y)-1]
        final Years term = arithmetic.yearsBetween(start.paymentDate(), end.paymentDate());
        final BigDecimalFraction multiplier = BigDecimalFraction.of(end.paymentAmount().over(start.paymentAmount()), term.decimalValue()).minus(1);
        final Percentage rate = Percentage.of(multiplier).times(100);
        return new SimpleFixedInterestRate(rate, arithmetic);
    }

    public SimpleFixedInterestRate(
            final Percentage rate,
            final DateArithmetic accrual) {
        super(rate, accrual);
    }

    @Override
    public SimpleFixedInterestRate with(final Percentage rate) {
        return new SimpleFixedInterestRate(rate, this.dateArithmetic());
    }

    @Override
    public <C extends CurrencyId> Money<C> accrue(final Money<C> money, final Years years) {
        return accrue(money, this.annualRate(), years);
    }

    @Override
    protected String type() {
        return "simple";
    }

    public static <C extends CurrencyId> Money<C> accrue(
            final Money<C> money,
            final Percentage annualRate,
            final Years term) {
        final BigDecimal multiplier = BigDecimal.ONE.add(term.times(annualRate).decimalValue());
        return money.times(multiplier);
    }

}
