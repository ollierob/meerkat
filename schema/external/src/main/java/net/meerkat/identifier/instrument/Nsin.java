package net.meerkat.identifier.instrument;

import net.meerkat.StringWrapper;
import net.meerkat.identifier.country.CountryIso;

/**
 * National Securities Identifying Number.
 *
 * @author ollie
 * @see <a href="https://en.wikipedia.org/wiki/NSIN">NSIN</a>
 */
public class Nsin
        extends StringWrapper
        implements InstrumentId {

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
