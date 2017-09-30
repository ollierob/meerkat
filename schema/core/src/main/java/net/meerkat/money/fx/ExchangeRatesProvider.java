package net.meerkat.money.fx;

import net.meerkat.money.fx.ExchangeRates;
import net.meerkat.money.fx.exception.UnavailableExchangeRateException;
import net.ollie.goat.data.Provider;

/**
 *
 * @author ollie
 */
public interface ExchangeRatesProvider<T> extends Provider<T, ExchangeRates> {

    @Override
    default ExchangeRates require(final T date) throws UnavailableExchangeRateException {
        return this.require(date, UnavailableExchangeRateException::new);
    }

}
