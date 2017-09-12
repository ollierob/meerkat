package net.meerkat.identifier.currency;

import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.Named;
import net.meerkat.identifier.country.CountryId;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class DefaultCurrency extends Named implements Currency {

    private final CurrencyIds currencyIds;
    private final CountryId issuer;

    public DefaultCurrency(
            final CurrencyIds currencyIds,
            final String name,
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
