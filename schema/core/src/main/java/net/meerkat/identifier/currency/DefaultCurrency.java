package net.meerkat.identifier.currency;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.Named;
import net.meerkat.identifier.country.CountryId;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class DefaultCurrency extends Named implements Currency {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "ids")
    private CurrencyIds currencyIds;

    @XmlElementRef(name = "issuer")
    private CountryId issuer;

    @Deprecated
    DefaultCurrency() {
    }

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
