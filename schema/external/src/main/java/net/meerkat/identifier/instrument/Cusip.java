package net.meerkat.identifier.instrument;

import net.meerkat.identifier.country.CountryIso;
import net.meerkat.identifier.instrument.algorithm.LuhnAlgorithm;

import javax.annotation.Nonnull;

/**
 * @author ollie
 */
public record Cusip(String value) implements Nsin, HasCheckDigit {

    @Override
    public char checkDigit() {
        return this.lastChar();
    }

    @Override
    public char computeCheckDigit() {
        return LuhnAlgorithm.check(this.value());
    }

    @Nonnull
    public Isin toIsin() {
        return Isin.create(CountryIso.US, this);
    }

}
