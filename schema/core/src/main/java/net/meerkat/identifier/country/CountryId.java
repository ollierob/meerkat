package net.meerkat.identifier.country;

/**
 *
 * @author Ollie
 */
public interface CountryId extends HasCountryId {

    @Override
    default CountryId countryId() {
        return this;
    }

}
