package net.meerkat.identifier.currency;

import net.ollie.goat.data.Provider;

/**
 * @author ollie
 */
public interface CurrencyProvider<C extends Currency> extends Provider<CurrencyId, C> {

    @Override
    default C require(final CurrencyId key) throws UnknownCurrencyException {
        return this.require(key, UnknownCurrencyException::new);
    }

}
