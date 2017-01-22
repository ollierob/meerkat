package net.ollie.meerkat.calculate.fx;

import java.util.Optional;

import javax.annotation.Nonnull;

import net.ollie.meerkat.money.Money;
import net.ollie.meerkat.money.currency.Currency;
import net.ollie.meerkat.money.currency.CurrencyPair;
import net.ollie.meerkat.money.fx.ExchangeRate;

/**
 * Snapshot of FX rates at a particular time.
 *
 * @author Ollie
 */
public interface ExchangeRates {

    default <F extends Currency, T extends Currency> ExchangeRate<F, T> rate(final F from, final T to)
            throws UnavailableExchangeRate {
        return this.maybeRate(from, to).orElseThrow(() -> new UnavailableExchangeRate(from, to));
    }

    <F extends Currency, T extends Currency> Optional<ExchangeRate<F, T>> maybeRate(F from, T to);

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
    default <F extends Currency, T extends Currency> ExchangeRate<F, T> baseRate(final CurrencyPair<F, T> pair)
            throws UnavailableExchangeRate {
        return this.rate(pair.base(), pair.counter());
    }

    @Nonnull
    default <F extends Currency, T extends Currency> ExchangeRate<T, F> counterRate(final CurrencyPair<F, T> pair)
            throws UnavailableExchangeRate {
        return this.rate(pair.counter(), pair.base());
    }

    class UnavailableExchangeRate extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public UnavailableExchangeRate(final Currency from, final Currency to) {
            super("Not available from " + from + " -> " + to);
        }

    }

}
