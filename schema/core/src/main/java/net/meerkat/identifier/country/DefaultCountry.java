package net.meerkat.identifier.country;

import javax.xml.bind.annotation.XmlAttribute;

import net.meerkat.money.currency.CurrencyId;

/**
 *
 * @author ollie
 */
public class DefaultCountry implements Country {

    @XmlAttribute(name = "id")
    private CountryId id;

    @XmlAttribute(name = "name", required = true)
    private String name;

    @Override
    public CountryId countryId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CurrencyId currency() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String name() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
