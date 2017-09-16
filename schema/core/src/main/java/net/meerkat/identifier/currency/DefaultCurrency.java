package net.meerkat.identifier.currency;

import net.meerkat.Named;
import net.meerkat.identifier.country.CountryId;

/**
 *
 * @author ollie
 */
public class DefaultCurrency extends Named implements Currency {

    private final CurrencyIds currencyIds;
    private final CountryId issuer;

    public DefaultCurrency(
            final String name,
            final CurrencyId currencyIds,
            final CountryId issuingCountry) {
        this(name, CurrencyIds.of(currencyIds), issuingCountry);
    }

    public DefaultCurrency(
            final String name,
            final CurrencyIds currencyIds,
            final CountryId issuingCountry) {
        super(name);
        this.currencyIds = currencyIds;
        this.issuer = issuingCountry;
    }

    @Override
    public CurrencyIds currencyIds() {
        return currencyIds;
    }

    @Override
    public CountryId countryId() {
        return issuer;
    }

}
