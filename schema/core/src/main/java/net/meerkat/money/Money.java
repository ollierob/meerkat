package net.meerkat.money;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.annotation.Nonnull;

import net.meerkat.money.currency.Currency;
import net.meerkat.money.currency.HasCurrency;
import net.meerkat.money.fx.ExchangeRate;
import net.ollie.goat.numeric.Numbers;
import net.ollie.goat.numeric.Numeric;
import net.ollie.goat.numeric.fraction.DecimalFraction;

/**
 *
 * @author Ollie
 */
public interface Money<C extends Currency>
        extends HasCurrency, Numeric.Summable<Money<C>>, Serializable {

    @Override
    C currency();

    @Nonnull
    Number amount();

    @Override
    Money<C> times(Number n);

    @Override
    Money<C> plus(@Nonnull Money<C> that);

    default <T extends Currency> Money<T> convert(final ExchangeRate<C, T> rate) {
        return rate.convert(this);
    }

    @Override
    default Money<C> minus(final Money<C> that) {
        return this.plus(that.negate());
    }

    @Nonnull
    default DecimalMoney<C> toDecimal() {
        return DecimalMoney.valueOf(this.currency(), this.amount());
    }

    @Nonnull
    default Money<C> over(final Number number) {
        return new FractionalMoney<>(this.currency(), DecimalFraction.of(this.amount(), number));
    }

    default String toString(@Nonnull final MoneyFormat convention) {
        return convention.toString(this);
    }

    static <C extends Currency> DecimalMoney<C> zero(final C currency) {
        return new DecimalMoney<>(currency, BigDecimal.ZERO);
    }

    static <C extends Currency> DecimalMoney<C> of(final long amount, final C currency) {
        return amount == 0
                ? zero(currency)
                : new DecimalMoney<>(currency, BigDecimal.valueOf(amount));
    }

    static boolean valuesEqual(final Money<?> left, final Money<?> right) {
        return Objects.equals(left.currency(), right.currency())
                && Numbers.equals(left.amount(), right.amount());
    }

    static int hashCode(final Money<?> money) {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(money.currency());
        hash = 29 * hash + Double.hashCode(money.doubleValue());
        return hash;
    }

}
