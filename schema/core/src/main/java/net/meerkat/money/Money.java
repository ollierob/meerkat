package net.meerkat.money;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.utils.Require;
import net.ollie.goat.numeric.BigDecimals;
import net.ollie.goat.numeric.Numbers;
import net.ollie.goat.numeric.Numeric;
import net.ollie.goat.numeric.fraction.BigDecimalFraction;

/**
 *
 * @author Ollie
 */
public interface Money<C extends CurrencyId>
        extends HasCurrencyId, Numeric.Summable<Money<C>>, Serializable {

    @Override
    C currencyId();

    @Nonnull
    Number amount();

    @Override
    Money<C> plus(@Nonnull Money<C> that);

    @Override
    Money<C> times(Number n);

    @Override
    default Money<C> reciprocal() {
        return Money.of(this.currencyId(), BigDecimalFraction.of(1, this.amount()));
    }

    default <T extends CurrencyId> Money<T> convert(final ExchangeRate<C, T> rate) {
        return rate.convert(this);
    }

    @Override
    default Money<C> minus(final Money<C> that) {
        return this.plus(that.negate());
    }

    @Nonnull
    default BigDecimalMoney<C> toDecimal() {
        return BigDecimalMoney.valueOf(this);
    }

    @Nonnull
    default Money<C> over(final Number number) {
        return new FractionalMoney<>(this.currencyId(), BigDecimalFraction.of(this.amount(), number));
    }

    default String toString(@Nonnull final MoneyFormat convention) {
        return convention.toString(this);
    }

    @Override
    default int compareTo(final Money<C> that) {
        Require.argumentsEqual(this.currencyId(), that.currencyId());
        return Numeric.Summable.super.compareTo(that);
    }

    static <C extends CurrencyId> BigDecimalMoney<C> zero(final C currency) {
        return new BigDecimalMoney<>(currency, BigDecimal.ZERO);
    }

    static <C extends CurrencyId> BigDecimalMoney<C> of(final C currency, final long amount) {
        return amount == 0
                ? zero(currency)
                : new BigDecimalMoney<>(currency, BigDecimal.valueOf(amount));
    }

    static <C extends CurrencyId> BigDecimalMoney<C> of(final C currency, final BigDecimal amount) {
        return amount.signum() == 0
                ? zero(currency)
                : new BigDecimalMoney<>(currency, amount);

    }

    static <C extends CurrencyId> BigDecimalMoney<C> of(final C currency, final Number amount) {
        return of(currency, BigDecimals.toBigDecimal(amount));
    }

    static boolean valuesEqual(final Money<?> left, final Money<?> right) {
        return Objects.equals(left.currencyId(), right.currencyId())
                && Numbers.equals(left.amount(), right.amount());
    }

    static int hashCode(final Money<?> money) {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(money.currencyId());
        hash = 29 * hash + Double.hashCode(money.doubleValue());
        return hash;
    }

}
