package net.ollie.meerkat.numeric.money;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.utils.numeric.Numbers;
import static net.ollie.meerkat.utils.numeric.Numbers.toBigDecimal;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class DecimalMoney<C extends CurrencyId> implements Money<C>, Serializable {

    private static final long serialVersionUID = 1L;

    public static <C extends CurrencyId> DecimalMoney<C> valueOf(final C currency, final Number amount) {
        return new DecimalMoney<>(currency, Numbers.toBigDecimal(amount));
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
    public C currencyId() {
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
    public Money<C> times(final Number that) {
        return new DecimalMoney<>(currency, amount.multiply(toBigDecimal(that)));
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
        return this.toString(MoneyFormat.CURRENCY_AMOUNT);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.currency);
        hash = 29 * hash + Objects.hashCode(this.amount);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DecimalMoney<?> other = (DecimalMoney) obj;
        if (!Objects.equals(this.currency, other.currency)) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        return true;
    }

}
