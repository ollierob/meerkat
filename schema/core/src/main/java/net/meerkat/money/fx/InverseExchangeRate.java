package net.meerkat.money.fx;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.identifier.currency.CurrencyId;
import net.ollie.goat.numeric.fraction.DecimalFraction;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class InverseExchangeRate<T extends CurrencyId, F extends CurrencyId>
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
    public DecimalFraction bidRate() {
        return inverse.bidRate().reciprocal();
    }

    @Override
    public DecimalFraction offerRate() {
        return inverse.offerRate().reciprocal();
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
