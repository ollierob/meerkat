package net.meerkat.identifier.currency;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.identifier.country.CountryId;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class DefaultCurrency implements Currency {

    @XmlElementRef(name = "ids")
    private CurrencyIds currencyIds;

    @XmlAttribute(name = "name")
    private String name;

    @XmlElementRef(name = "issuer")
    private CountryId issuer;

    @Deprecated
    DefaultCurrency() {
    }

    public DefaultCurrency(
            final CurrencyIds currencyIds,
            final String name,
            final CountryId issuingCountry) {
        this.currencyIds = currencyIds;
        this.name = name;
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

    @Override
    public String name() {
        return name;
    }

}
