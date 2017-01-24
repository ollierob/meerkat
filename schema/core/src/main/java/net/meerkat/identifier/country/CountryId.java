package net.meerkat.identifier.country;

import net.meerkat.utils.Classes.Castable;

/**
 *
 * @author Ollie
 */
public interface CountryId extends HasCountryId, Castable {

    @Override
    default CountryId countryId() {
        return this;
    }

}
