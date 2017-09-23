package net.meerkat.identifier.currency;

import net.meerkat.identifier.country.CountryId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.NamedInstrument;

/**
 *
 * @author Ollie
 */
public class DefaultCurrency extends NamedInstrument implements Currency {

    private final CountryId countryId;

    public DefaultCurrency(final String name, final InstrumentIds ids, final CountryId countryId) {
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
