package net.meerkat.money.fx;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.money.Money;
import net.ollie.goat.numeric.fraction.DecimalFraction;
import net.meerkat.money.currency.CurrencyId;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class RatioExchangeRate<F extends CurrencyId, T extends CurrencyId>
        implements ExchangeRate<F, T>, Externalizable {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "from")
    private Money<F> from;

    @XmlElementRef(name = "to")
    private Money<T> to;

    @Deprecated
    RatioExchangeRate() {
    }

    public RatioExchangeRate(final Money<F> from, final Money<T> to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public F from() {
        return from.currencyId();
    }

    @Override
    public T to() {
        return to.currencyId();
    }

    @Override
    public DecimalFraction rate() {
        return DecimalFraction.of(to.amount(), from.amount());
    }

    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeObject(from);
        out.writeObject(to);
    }

    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        from = (Money<F>) in.readObject();
        to = (Money<T>) in.readObject();
    }

}
