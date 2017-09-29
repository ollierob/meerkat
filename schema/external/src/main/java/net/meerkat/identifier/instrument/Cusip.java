package net.meerkat.identifier.instrument;

import javax.annotation.Nonnull;

import net.meerkat.identifier.country.CountryIso;
import net.meerkat.utils.algorithm.LuhnAlgorithm;

/**
 *
 * @author ollie
 */
public class Cusip
        extends Nsin
        implements HasCheckDigit {

    public Cusip(final String value) {
        super(value);
    }

    @Override
    public char checkDigit() {
        return this.last();
    }

    @Override
    public char computeCheckDigit() {
        return LuhnAlgorithm.check(this.value());
    }

    @Override
    protected String isinPart() {
        return this.value();
    }

    @Nonnull
    public Isin toIsin() {
        return Isin.create(CountryIso.US, this);
    }

}
