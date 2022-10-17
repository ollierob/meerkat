package net.meerkat.identifier.currency;

import net.meerkat.identifier.country.CountryIso;
import net.meerkat.identifier.country.HasCountryId;

/**
 *
 * @author ollie
 */
public abstract class NationalCurrencyIso
        extends AbstractCurrencyIso
        implements HasCountryId {

    @Override
    public CountryIso countryId() {
        return CountryIso.valueOf(this.value().substring(0, 2));
    }

}
