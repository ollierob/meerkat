package net.meerkat.money.fx;

import net.meerkat.money.fx.exception.UnavailableExchangeRateException;

import java.util.Objects;
import java.util.Optional;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.CurrencyIdPair;
import net.meerkat.money.Money;

/**
 * Snapshot of FX rates at a particular time.
 *
 * @author Ollie
 */
public interface ExchangeRates {

    default <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> rate(final F from, final T to)
            throws UnavailableExchangeRateException {
        return this.maybeRate(from, to).orElseThrow(() -> new UnavailableExchangeRateException(from, to));
    }

    <F extends CurrencyId, T extends CurrencyId> Optional<ExchangeRate<F, T>> maybeRate(F from, T to);

    @Nonnull
    @SuppressWarnings("unchecked")
    default <F extends CurrencyId, T extends CurrencyId> Money<T> convert(
            @Nonnull final Money<F> money,
            @Nonnull final T to) {
        final F from = money.currencyId();
        return Objects.equals(from, to)
                ? (Money<T>) money
                : this.rate(from, to).convertAtMid(money);
    }

    @Nonnull
    default <F extends CurrencyId, T extends CurrencyId> Money<F> add(final Money<F> left, final Money<T> right) {
        return left.plus(this.convert(right, left.currencyId()));
    }

    @Nonnull
    default <F extends CurrencyId, T extends CurrencyId> Money<F> subtract(final Money<F> minuend, final Money<T> subtrahend) {
        return minuend.minus(this.convert(subtrahend, minuend.currencyId()));
    }

    @Nonnull
    default <F extends CurrencyId, T extends CurrencyId> ExchangeRate<F, T> baseRate(final CurrencyIdPair<F, T> pair)
            throws UnavailableExchangeRateException {
        return this.rate(pair.baseCurrencyId(), pair.counterCurrencyId());
    }

    @Nonnull
    default <F extends CurrencyId, T extends CurrencyId> ExchangeRate<T, F> counterRate(final CurrencyIdPair<F, T> pair)
            throws UnavailableExchangeRateException {
        return this.rate(pair.counterCurrencyId(), pair.baseCurrencyId());
    }


}
