package net.ollie.meerkat.calculate.fx;

import javax.annotation.Nonnull;

import net.ollie.goat.money.currency.Currency;
import net.ollie.goat.money.Money;
import net.ollie.goat.money.fx.ExchangeRate;

/**
 *
 * @author Ollie
 */
public interface ExchangeRateCalculator {

    <F extends Currency, T extends Currency> ExchangeRate<F, T> rate(F from, T to);

    @Nonnull
    default <F extends Currency, T extends Currency> Money<T> convert(@Nonnull final Money<F> money, @Nonnull final T into) {
        return this.rate(money.currency(), into).convert(money);
    }

    @Nonnull
    default <F extends Currency, T extends Currency> Money<F> add(final Money<F> left, final Money<T> right) {
        return left.plus(this.convert(right, left.currency()));
    }

    @Nonnull
    default <F extends Currency, T extends Currency> Money<F> subtract(final Money<F> minuend, final Money<T> subtrahend) {
        return minuend.minus(this.convert(subtrahend, minuend.currency()));
    }

}
