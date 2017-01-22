package net.ollie.meerkat.money.fx;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.money.currency.Currency;
import net.ollie.goat.numeric.fraction.DecimalFraction;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class InverseExchangeRate<T extends Currency, F extends Currency>
        implements ExchangeRate<T, F>, Externalizable {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "inverse")
    private ExchangeRate<F, T> inverse;

    @Deprecated
    InverseExchangeRate() {
    }

    public InverseExchangeRate(final ExchangeRate<F, T> delegate) {
        this.inverse = delegate;
    }

    @Override
    public T from() {
        return inverse.to();
    }

    @Override
    public F to() {
        return inverse.from();
    }

    @Override
    public DecimalFraction rate() {
        return inverse.rate().inverse();
    }

    @Override
    public ExchangeRate<F, T> inverse() {
        return inverse;
    }

    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeObject(inverse);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        inverse = (ExchangeRate<F, T>) in.readObject();
    }

}
