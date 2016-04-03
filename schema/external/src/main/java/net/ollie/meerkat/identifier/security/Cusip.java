package net.ollie.meerkat.identifier.security;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.identifier.country.CountryIso;
import net.ollie.meerkat.utils.algorithm.LuhnAlgorithm;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class Cusip
        extends Nsin
        implements HasCheckDigit {

    @Deprecated
    Cusip() {
    }

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
