package net.meerkat.identifier.currency;

import net.ollie.goat.data.Provider;

/**
 *
 * @author ollie
 */
public interface CurrencyProvider extends Provider<CurrencyId, Currency> {

    @Override
    default Currency require(final CurrencyId key) throws UnknownCurrencyException {
        return this.require(key, UnknownCurrencyException::new);
    }

}
