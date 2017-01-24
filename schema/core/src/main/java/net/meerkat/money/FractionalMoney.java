package net.meerkat.money;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.goat.numeric.fraction.DecimalFraction;
import net.meerkat.money.currency.CurrencyId;

/**
 *
 * @author Ollie
 */
public class FractionalMoney<C extends CurrencyId>
        implements Money<C>, Externalizable {

    private static final long serialVersionUID = 1L;

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
    public FractionalMoney<C> times(final Number n) {
        return this.with(fraction.times(n));
    }

    @Override
    @Deprecated
    public FractionalMoney<C> times(final Number that, final RoundingMode rounding) {
        return this.times(that);
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return fraction.decimalValue(context);
    }

    @Override
    public String toString() {
        return this.toString(MoneyFormat.SYMBOL_AMOUNT);
    }

    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeObject(currency);
        out.writeObject(fraction);
    }

    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        currency = (C) in.readObject();
        fraction = (DecimalFraction) in.readObject();
    }

}
