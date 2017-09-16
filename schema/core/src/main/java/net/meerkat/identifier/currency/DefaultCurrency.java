package net.meerkat.identifier.currency;

import net.meerkat.instrument.IdentifiedInstrument;

/**
 *
 * @author ollie
 */
public class DefaultCurrency extends IdentifiedInstrument implements Currency {

    public DefaultCurrency(final CurrencyIds currencyIds) {
        super(currencyIds);
    }

    @Override
    public CurrencyIds currencyIds() {
        throw new UnsupportedOperationException(); //TODO
    }

}
