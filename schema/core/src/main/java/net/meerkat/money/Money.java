package net.meerkat.money;

import net.coljate.collection.Collection;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.HasCurrencyId;
import net.meerkat.money.fx.ExchangeRate;
import net.meerkat.numeric.Numbers;
import net.meerkat.numeric.Numeric;
import net.meerkat.numeric.decimal.BigDecimalFraction;
import net.meerkat.numeric.decimal.BigDecimals;
import net.meerkat.objects.Arguments;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Ollie
 */
public interface Money<C extends CurrencyId>
        extends HasCurrencyId, Numeric.Summable<Money<C>>, Serializable {

    @Override
    C currencyId();

    @Nonnull
    @Override
    default Number value() {
        return this.decimalValue();
    }

    @Override
    Money<C> plus(@Nonnull Money<C> that);

    @Override
    Money<C> times(Number n);

    @Override
    default Money<C> reciprocal() {
        return Money.of(this.currencyId(), BigDecimalFraction.of(1, this.value()));
    }

    default <T extends CurrencyId> Money<T> convert(final ExchangeRate<C, T> rate) {
        return rate.convertAtMid(this);
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
        return new FractionalMoney<>(this.currencyId(), BigDecimalFraction.of(this.value(), number));
    }

    default Number over(final Money<C> that) {
        return BigDecimalFraction.of(this.value(), that.value());
    }

    default String toString(@Nonnull final MoneyFormat convention) {
        return convention.toString(this);
    }

    @Override
    default int compareTo(final Money<C> that) {
        Arguments.requireEqual(this.currencyId(), that.currencyId());
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

    static <C extends CurrencyId> Money<C> of(final C currency, final double amount) {
        return amount == 0
                ? zero(currency)
                : new DoubleMoney<>(currency, amount);
    }

    static <C extends CurrencyId> BigDecimalMoney<C> of(final C currency, final Number amount) {
        return of(currency, BigDecimals.toBigDecimal(amount));
    }

    static <C extends CurrencyId> Money<C> sum(final Collection<Money<C>> monies) {
        return monies.reduce(Money::nullSafePlus);
    }

    static <C extends CurrencyId> Money<C> mean(final Collection<Money<C>> monies) {
        return sum(monies).over(monies.count());
    }

    static boolean valuesEqual(final Money<?> left, final Money<?> right) {
        return Objects.equals(left.currencyId(), right.currencyId())
                && Numbers.equals(left.value(), right.value());
    }

    static int hashCode(final Money<?> money) {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(money.currencyId());
        hash = 29 * hash + Double.hashCode(money.doubleValue());
        return hash;
    }

    static <C extends CurrencyId> Money<C> nullSafePlus(final Money<C> left, final Money<C> right) {
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return left.plus(right);
        }
    }

}
