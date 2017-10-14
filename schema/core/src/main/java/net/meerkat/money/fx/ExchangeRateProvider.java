package net.meerkat.money.fx;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.fx.exception.UnavailableExchangeRateException;
import net.ollie.goat.data.Provider;

/**
 *
 * @author Ollie
 */
public interface ExchangeRateProvider<T> extends Provider<T, ExchangeRateSnapshot> {

    @Override
    default ExchangeRateSnapshot require(final T temporal) throws UnavailableExchangeRateException {
        return this.require(temporal, UnavailableExchangeRateException::new);
    }

    @Nonnull
    default <A extends CurrencyId, B extends CurrencyId> ExchangeRate<A, B> rate(final T temporal, final A from, final B to) throws UnavailableExchangeRateException {
        return this.require(temporal).rate(from, to);
    }

}
