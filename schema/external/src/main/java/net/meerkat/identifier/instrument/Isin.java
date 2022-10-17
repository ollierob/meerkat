package net.meerkat.identifier.instrument;

import net.meerkat.Explainable;
import net.meerkat.identifier.Iso;
import net.meerkat.identifier.country.CountryId;
import net.meerkat.identifier.country.CountryIso;
import net.meerkat.identifier.country.HasCountryId;
import net.meerkat.identifier.instrument.algorithm.LuhnAlgorithm;
import net.meerkat.instrument.Security;

import javax.annotation.Nonnull;

/**
 * ISIN is a {@link Security security} identifier, used mainly by bonds and equities.
 *
 * @author Ollie
 */
public record Isin(String value) implements InstrumentId, Iso, HasCountryId, HasCheckDigit, Explainable {

    public static Isin create(final CountryIso country, final Nsin nsin) {
        final var checkDigit = computeCheckDigit(country.value() + nsin.isinPart());
        return new Isin(country.value() + nsin.value() + checkDigit);
    }

    @Nonnull
    @Override
    public CountryId countryId() {
        return CountryIso.valueOf(value.substring(0, 2));
    }

    public static char computeCheckDigit(final String isin) {
        final var numeric = toCheckDigit(isin.charAt(0))
                + toCheckDigit(isin.charAt(1))
                + isin.substring(2, isin.length());
        return LuhnAlgorithm.check(numeric);
    }

    @Override
    public char computeCheckDigit() {
        return computeCheckDigit(value.substring(value.length() - 2));
    }

    private static String toCheckDigit(final char c) {
        return String.valueOf((int) Character.toUpperCase(c) - 55);
    }

    @Override
    public char checkDigit() {
        return value.charAt(value.length() - 1);
    }

    @Override
    public String standard() {
        return "6166";
    }

    @Override
    public String toString() {
        return this.value();
    }

    @Override
    public ExplanationBuilder explain() {
        return this.explanationBuilder().put("value", value);
    }

}
