package net.meerkat.identifier.currency;

import javax.xml.bind.annotation.XmlTransient;

import net.meerkat.identifier.country.CountryIso;
import net.ollie.meerkat.identifier.country.HasCountryId;

/**
 *
 * @author ollie
 */
@XmlTransient
public abstract class NationalCurrencyIso
        extends AbstractCurrencyIso
        implements HasCountryId {

    private static final long serialVersionUID = 1L;

    @Override
    public CountryIso countryId() {
        return CountryIso.valueOf(this.value().substring(0, 2));
    }

}
