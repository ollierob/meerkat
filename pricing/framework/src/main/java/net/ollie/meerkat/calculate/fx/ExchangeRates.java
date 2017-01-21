package net.ollie.meerkat.calculate.fx;

import javax.annotation.Nonnull;

import net.ollie.goat.money.Money;
import net.ollie.goat.money.currency.Currency;
import net.ollie.goat.money.currency.CurrencyPair;
import net.ollie.goat.money.fx.ExchangeRate;

/**
 * Snapshot of FX rates at a particular time.
 *
 * @author Ollie
 */
public interface ExchangeRates {

    <F extends Currency, T extends Currency> ExchangeRate<F, T> rate(F from, T to);

    @Nonnull
    default <F extends Currency, T extends Currency> Money<T> convert(
            @Nonnull final Money<F> money,
            @Nonnull final T to) {
        final F from = money.currency();
        return from == to
                ? (Money<T>) money
                : this.rate(from, to).convert(money);
    }

    @Nonnull
    default <F extends Currency, T extends Currency> Money<F> add(final Money<F> left, final Money<T> right) {
        return left.plus(this.convert(right, left.currency()));
    }

    @Nonnull
    default <F extends Currency, T extends Currency> Money<F> subtract(final Money<F> minuend, final Money<T> subtrahend) {
        return minuend.minus(this.convert(subtrahend, minuend.currency()));
    }

    @Nonnull
    default <F extends Currency, T extends Currency> ExchangeRate<F, T> baseRate(final CurrencyPair<F, T> pair) {
        return this.rate(pair.base(), pair.counter());
    }

    @Nonnull
    default <F extends Currency, T extends Currency> ExchangeRate<T, F> counterRate(final CurrencyPair<F, T> pair) {
        return this.rate(pair.counter(), pair.base());
    }

}
