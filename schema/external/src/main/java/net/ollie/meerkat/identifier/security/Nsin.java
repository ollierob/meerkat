package net.ollie.meerkat.identifier.security;

import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.StringWrapper;
import net.ollie.meerkat.identifier.country.CountryIso;

/**
 * National Securities Identifying Number.
 *
 * @author ollie
 * @see <a href="https://en.wikipedia.org/wiki/NSIN">NSIN</a>
 */
@XmlRootElement
public class Nsin
        extends StringWrapper
        implements SecurityId {

    private static final long serialVersionUID = 1L;

    @Deprecated
    protected Nsin() {
    }

    public Nsin(final String value) {
        super(value);
    }

    @Override
    public String value() {
        return super.value();
    }

    public Isin toIsin(final CountryIso country) {
        return Isin.create(country, this);
    }

    protected String isinPart() {
        return String.format("%9s", this.value()).replace(' ', '0');
    }

}
