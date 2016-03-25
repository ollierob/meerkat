package net.ollie.meerkat.identifier.country;

import javax.xml.bind.annotation.XmlAttribute;

/**
 *
 * @author Ollie
 */
public class CountrySubdivisionIso extends CountryIso {

    private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "subdivision")
    private String subdivision;

    @Deprecated
    CountrySubdivisionIso() {
    }

    public CountrySubdivisionIso(final String country, final String subdivision) {
        super(country);
        this.subdivision = subdivision;
    }

    @Override
    public String value() {
        return super.value() + '-' + subdivision;
    }

    @Override
    public String standard() {
        return "3166-2";
    }

}
