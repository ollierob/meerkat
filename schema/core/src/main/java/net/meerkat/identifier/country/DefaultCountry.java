package net.meerkat.identifier.country;

import net.meerkat.identifier.currency.CurrencyIds;

/**
 * @author ollie
 */
public class DefaultCountry implements Country {

    private final CountryId id;
    private final String name;
    private final CurrencyIds currencyIds;

    public DefaultCountry(CountryId id, String name, CurrencyIds currencyIds) {
        this.id = id;
        this.name = name;
        this.currencyIds = currencyIds;
    }

    @Override
    public CountryId countryId() {
        return id;
    }

    @Override
    public CurrencyIds currencyIds() {
        return currencyIds;
    }

    @Override
    public String name() {
        return name;
    }

}
