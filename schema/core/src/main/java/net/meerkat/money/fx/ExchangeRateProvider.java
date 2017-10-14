package net.meerkat.money.fx;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.CurrencyIdPair;
import net.meerkat.money.fx.exception.UnavailableExchangeRateException;
import net.ollie.goat.data.CompositeProvider;

/**
 *
 * @author Ollie
 */
public interface ExchangeRateProvider<T> extends CompositeProvider<T, CurrencyIdPair<?, ?>, ExchangeRate<?, ?>, ExchangeRateSnapshot> {

    @Override
    default ExchangeRateSnapshot require(final T temporal) throws UnavailableExchangeRateException {
        return this.require(temporal, UnavailableExchangeRateException::new);
    }

    @Override
    default ExchangeRate<?, ?> require(final T temporal, final CurrencyIdPair<?, ?> pair) {
        return this.rate(temporal, pair.baseCurrencyId(), pair.counterCurrencyId());
    }

    @Nonnull
    default <A extends CurrencyId, B extends CurrencyId> ExchangeRate<A, B> rate(final T temporal, final A from, final B to) throws UnavailableExchangeRateException {
        return this.require(temporal).rate(from, to);
    }

}
