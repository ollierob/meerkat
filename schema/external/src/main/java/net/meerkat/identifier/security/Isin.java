package net.meerkat.identifier.security;

import java.util.Objects;
import java.util.Optional;

import javax.annotation.Nonnull;

import net.meerkat.Explainable;
import net.meerkat.identifier.country.CountryIso;
import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.utils.algorithm.LuhnAlgorithm;

/**
 *
 * @author Ollie
 */
public class Isin implements InstrumentId, HasCheckDigit, HasNsin, Explainable {

    private static final long serialVersionUID = 1L;

    public static Isin valueOf(final String isin) {
        //TODO check length
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
    public Optional<Nsin> nsin() {
        return Optional.of(nsin);
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

    public String value() {
        return country.value()
                + nsin.isinPart()
                + checkDigit;
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
