package net.meerkat.identifier.instrument;

import net.meerkat.identifier.country.CountryIso;

import javax.annotation.Nonnull;

/**
 * National Securities Identifying Number.
 *
 * @author ollie
 * @see <a href="https://en.wikipedia.org/wiki/NSIN">NSIN</a>
 */
public interface Nsin extends InstrumentId {

    @Nonnull
    String value();

    default char lastChar() {
        final var value = this.value();
        return value.charAt(value.length() - 1);
    }

    default Isin toIsin(final CountryIso country) {
        return Isin.create(country, this);
    }

    default String isinPart() {
        return String.format("%9s", this.value()).replace(' ', '0');
    }

    static Nsin unknownType(final String value) {
        return () -> value;
    }

}
