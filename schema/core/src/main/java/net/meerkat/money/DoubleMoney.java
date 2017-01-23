package net.meerkat.money;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.money.currency.CurrencyId;

/**
 *
 * @author ollie
 */
public class DoubleMoney<C extends CurrencyId>
        implements Money<C>, Externalizable {

    @XmlAttribute(name = "amount")
    private double amount;

    @XmlElementRef(name = "currency")
    private C currency;

    @Deprecated
    DoubleMoney() {
    }

    public DoubleMoney(double amount, C currency) {
        this.amount = amount;
        this.currency = currency;
    }

    @Override
    public C currency() {
        return currency;
    }

    @Override
    public Double amount() {
        return amount;
    }

    @Override
    public double doubleValue() {
        return amount;
    }

    @Override
    public DoubleMoney<C> times(final Number n) {
        return new DoubleMoney<>(amount * n.doubleValue(), currency);
    }

    @Override
    public DoubleMoney<C> plus(final Money<C> that) {
        return new DoubleMoney<>(amount + that.doubleValue(), currency);
    }

    @Override
    public Money<C> times(final Number that, final RoundingMode rounding) {
        return new DoubleMoney<>(amount * that.doubleValue(), currency);
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return BigDecimal.valueOf(amount).round(context);
    }

    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeDouble(amount);
        out.writeObject(currency);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
