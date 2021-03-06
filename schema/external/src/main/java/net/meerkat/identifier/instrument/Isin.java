package net.meerkat.identifier.instrument;

import net.meerkat.Explainable;
import net.meerkat.identifier.Iso;
import net.meerkat.identifier.country.CountryIso;
import net.meerkat.identifier.instrument.algorithm.LuhnAlgorithm;
import net.meerkat.instrument.Security;
import net.meerkat.objects.Arguments;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * ISIN is a {@link Security security} identifier, used mainly by bonds and equities.
 *
 * @author Ollie
 */
public class Isin implements InstrumentId, Iso, HasCheckDigit, Explainable {

    public static Isin valueOf(final String isin) {
        Arguments.require(isin, i -> i.length() == 12, i -> "Invalid ISIN: " + i);
        final CountryIso country = CountryIso.valueOf(isin.substring(0, 2));
        final Nsin nsin = new Nsin(isin.substring(2, 11));
        final char checkDigit = isin.charAt(11);
        return new Isin(country, nsin, checkDigit);
    }

    public static Isin create(final CountryIso country, final Nsin nsin) {
        final char checkDigit = computeCheckDigit(country.value() + nsin.isinPart());
        return new Isin(country, nsin, checkDigit);
    }

    private final CountryIso country;
    private final Nsin nsin;
    private final char checkDigit;

    public Isin(final CountryIso country, final Nsin nsin, char checkDigit) {
        this.country = country;
        this.nsin = nsin;
        this.checkDigit = checkDigit;
    }

    public CountryIso country() {
        return country;
    }

    @Override
    public InstrumentIds instrumentIds() {
        return InstrumentIds.of(this, nsin);
    }

    public Nsin nsin() {
        return nsin;
    }

    @Override
    public char checkDigit() {
        return checkDigit;
    }

    public static char computeCheckDigit(final String isin) {
        final String numeric = toCheckDigit(isin.charAt(0))
                + toCheckDigit(isin.charAt(1))
                + isin.substring(2, isin.length());
        return LuhnAlgorithm.check(numeric);
    }

    @Override
    public char computeCheckDigit() {
        return computeCheckDigit(country.value() + nsin.isinPart());
    }

    private static String toCheckDigit(final char c) {
        return String.valueOf((int) Character.toUpperCase(c) - 55);
    }

    @Override
    public char first() {
        return country.first();
    }

    @Override
    public String value() {
        return country.value()
                + nsin.isinPart()
                + checkDigit;
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
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.country);
        hash = 23 * hash + Objects.hashCode(this.nsin);
        hash = 23 * hash + this.checkDigit;
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof Isin
                && this.equals((Isin) obj);
    }

    public boolean equals(@Nonnull final Isin that) {
        return this.checkDigit() == that.checkDigit()
                && Objects.equals(this.country(), that.country())
                && Objects.equals(this.nsin(), that.nsin());
    }

    @Override
    public ExplanationBuilder explain() {
        return this.explanationBuilder()
                .put("country", country)
                .put("nsin", nsin)
                .put("check digit", checkDigit);
    }

}
