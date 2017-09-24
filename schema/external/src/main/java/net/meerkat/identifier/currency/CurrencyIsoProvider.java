package net.meerkat.identifier.currency;

import net.ollie.goat.data.Provider;

/**
 *
 * @author ollie
 */
public interface CurrencyIsoProvider extends Provider<CurrencyId, CurrencyIso> {

    @Override
    default CurrencyIso require(final CurrencyId id) throws UnknownCurrencyException {
        return this.require(id, UnknownCurrencyException::new);
    }

}
