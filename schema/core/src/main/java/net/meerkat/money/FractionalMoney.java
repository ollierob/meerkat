package net.meerkat.money;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.numeric.decimal.BigDecimalFraction;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @author Ollie
 */
public record FractionalMoney<C extends CurrencyId>(C currencyId, BigDecimalFraction value) implements Money<C> {

    public FractionalMoney<C> with(final BigDecimalFraction fraction) {
        return new FractionalMoney<>(currencyId, fraction);
    }

    @Override
    public FractionalMoney<C> plus(final Money<C> that) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public FractionalMoney<C> times(final Number n) {
        return this.with(value.times(n));
    }

    @Override
    @Deprecated
    public FractionalMoney<C> times(final Number that, final RoundingMode rounding) {
        return this.times(that);
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return value.decimalValue(context);
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof Money<?> m && this.equals(m);
    }

    @Override
    public int hashCode() {
        return Money.hashCode(this);
    }

    @Override
    public String toString() {
        return this.toString(MoneyFormat.UNIQUE_SYMBOL_AMOUNT);
    }

}
