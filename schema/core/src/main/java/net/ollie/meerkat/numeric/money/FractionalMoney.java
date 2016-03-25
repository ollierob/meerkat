package net.ollie.meerkat.numeric.money;

import java.math.BigDecimal;
import java.math.MathContext;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.DecimalFraction;

/**
 *
 * @author Ollie
 */
public class FractionalMoney<C extends CurrencyId> implements Money<C> {

    @XmlElementRef(name = "currency")
    private C currency;

    @XmlElement(name = "fraction")
    private DecimalFraction fraction;

    FractionalMoney() {
    }

    public FractionalMoney(final C currency, final DecimalFraction fraction) {
        this.currency = currency;
        this.fraction = fraction;
    }

    public FractionalMoney<C> with(final DecimalFraction fraction) {
        return new FractionalMoney<>(currency, fraction);
    }

    @Override
    public C currencyId() {
        return currency;
    }

    @Override
    public DecimalFraction amount() {
        return fraction;
    }

    @Override
    public FractionalMoney<C> plus(final Money<C> that) {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public FractionalMoney<C> times(final Number that) {
        return this.with(fraction.times(that));
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return fraction.decimalValue(context);
    }

}
