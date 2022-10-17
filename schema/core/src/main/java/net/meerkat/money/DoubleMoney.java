package net.meerkat.money;

import net.meerkat.identifier.currency.CurrencyId;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @author ollie
 */
public record DoubleMoney<C extends CurrencyId>(C currencyId, double amount) implements Money<C> {

    @Override
    public double doubleValue() {
        return amount;
    }

    @Override
    public DoubleMoney<C> times(final Number n) {
        return new DoubleMoney<>(currencyId, amount * n.doubleValue());
    }

    @Override
    public DoubleMoney<C> plus(final Money<C> that) {
        return new DoubleMoney<>(currencyId, amount + that.doubleValue());
    }

    @Override
    public Money<C> times(final Number that, final RoundingMode rounding) {
        return new DoubleMoney<>(currencyId, amount * that.doubleValue());
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return BigDecimal.valueOf(amount).round(context);
    }

    @Override
    public DoubleMoney<C> reciprocal() {
        return new DoubleMoney<>(currencyId, 1 / amount);
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof Money<?> m && this.equals(m);
    }

    @Override
    public int hashCode() {
        return Money.hashCode(this);
    }

}
