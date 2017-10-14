package net.meerkat.money.fx;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.fx.exception.UnavailableExchangeRateException;
import net.ollie.goat.data.Provider;

/**
 *
 * @author ollie
 */
public interface ExchangeRatesProvider<T> extends Provider<T, ExchangeRates> {

    @Override
    default ExchangeRates require(final T temporal) throws UnavailableExchangeRateException {
        return this.require(temporal, UnavailableExchangeRateException::new);
    }

    default <A extends CurrencyId, B extends CurrencyId> ExchangeRate<A, B> rate(final T temporal, final A from, final B to) throws UnavailableExchangeRateException {
        return this.require(temporal).rate(from, to);
    }

}
