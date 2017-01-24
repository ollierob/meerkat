package net.meerkat.identifier.country;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.identifier.currency.CurrencyIds;

/**
 *
 * @author ollie
 */
public class DefaultCountry implements Country {

    @XmlElementRef(name = "id")
    private CountryId id;

    @XmlAttribute(name = "name", required = true)
    private String name;

    @XmlElementRef(name = "currencies")
    private CurrencyIds currencyIds;

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
