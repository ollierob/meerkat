package net.meerkat.money;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.numeric.decimal.BigDecimals;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * @author Ollie
 */
public record BigDecimalMoney<C extends CurrencyId>(C currencyId, BigDecimal value) implements Money<C> {

    public static <C extends CurrencyId> BigDecimalMoney<C> valueOf(final Money<C> money) {
        return money instanceof BigDecimalMoney
                ? (BigDecimalMoney<C>) money
                : valueOf(money.currencyId(), money.value());
    }

    public static <C extends CurrencyId> BigDecimalMoney<C> valueOf(final C currency, final Number amount) {
        return new BigDecimalMoney<>(currency, BigDecimals.toBigDecimal(amount));
    }

    public static <C extends CurrencyId> BigDecimalMoney<C> valueOf(final C currency, final double amount) {
        return new BigDecimalMoney<>(currency, BigDecimal.valueOf(amount));
    }

    @Override
    public boolean isZero() {
        return value.signum() == 0;
    }

    @Override
    public Money<C> plus(final Money<C> that) {
        return that.isZero()
                ? this
                : new BigDecimalMoney<>(currencyId, value.add(that.decimalValue()));
    }

    @Override
    public BigDecimalMoney<C> times(final Number n) {
        return new BigDecimalMoney<>(currencyId, value.multiply(BigDecimals.toBigDecimal(n)));
    }

    @Override
    @Deprecated
    public Money<C> times(final Number that, final RoundingMode rounding) {
        return this.times(that);
    }

    @Override
    public BigDecimalMoney<C> negate() {
        return new BigDecimalMoney<>(currencyId, value.negate());
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return value.round(context);
    }

    @Override
    public BigDecimalMoney<C> toDecimal() {
        return this;
    }

    @Override
    public String toString() {
        return this.toString(currencyId.format());
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof Money<?> m
                && this.equals(m);
    }

    @Override
    public int hashCode() {
        return Money.hashCode(this);
    }

}
