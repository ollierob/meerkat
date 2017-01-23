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
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.goat.numeric.BigDecimals;
import net.meerkat.money.currency.CurrencyId;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class DecimalMoney<C extends CurrencyId>
        implements Money<C>, Externalizable {

    private static final long serialVersionUID = 1L;

    public static <C extends CurrencyId> DecimalMoney<C> valueOf(final C currency, final Number amount) {
        return new DecimalMoney<>(currency, BigDecimals.toBigDecimal(amount));
    }

    public static <C extends CurrencyId> DecimalMoney<C> valueOf(final C currency, final double amount) {
        return new DecimalMoney<>(currency, BigDecimal.valueOf(amount));
    }

    @XmlElementRef(name = "currency")
    private C currency;

    @XmlAttribute(name = "amount")
    private BigDecimal amount;

    @Deprecated
    DecimalMoney() {
    }

    public DecimalMoney(final C currency, final BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    @Override
    public C currency() {
        return currency;
    }

    @Override
    public BigDecimal amount() {
        return amount;
    }

    @Override
    public Money<C> plus(final Money<C> that) {
        return that.isZero()
                ? this
                : new DecimalMoney<>(currency, amount.add(that.decimalValue()));
    }

    @Override
    public DecimalMoney<C> times(final Number n) {
        return new DecimalMoney<>(currency, amount.multiply(BigDecimals.toBigDecimal(n)));
    }

    @Override
    @Deprecated
    public Money<C> times(final Number that, final RoundingMode rounding) {
        return this.times(that);
    }

    @Override
    public DecimalMoney<C> negate() {
        return new DecimalMoney<>(currency, amount.negate());
    }

    @Override
    public BigDecimal decimalValue(final MathContext context) {
        return amount.round(context);
    }

    @Override
    public DecimalMoney<C> toDecimal() {
        return this;
    }

    @Override
    public String toString() {
        return this.toString(currency.format());
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof Money
                && Money.valuesEqual(this, (Money) obj);
    }

    @Override
    public int hashCode() {
        return Money.hashCode(this);
    }

    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeObject(currency);
        out.writeObject(amount);
    }

    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        currency = (C) in.readObject();
        amount = (BigDecimal) in.readObject();
    }

}
