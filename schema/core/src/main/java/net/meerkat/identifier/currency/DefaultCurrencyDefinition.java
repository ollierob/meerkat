package net.meerkat.identifier.currency;

import net.meerkat.identifier.country.CountryId;
import net.meerkat.instrument.NamedInstrument;

/**
 *
 * @author Ollie
 */
public class DefaultCurrencyDefinition extends NamedInstrument implements CurrencyDefinition {

    private final CountryId countryId;

    public DefaultCurrencyDefinition(final String name, final CurrencyIds ids, final CountryId countryId) {
        super(name, ids);
        this.countryId = countryId;
    }

    @Override
    public CountryId countryId() {
        return countryId;
    }

    @Override
    public <R> R handleWith(Handler<R> handler) {
        throw new UnsupportedOperationException(); //TODO
    }

}
