package net.ollie.meerkat.numeric.money.fx;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.DecimalFraction;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class InverseExchangeRate<T extends CurrencyId, F extends CurrencyId> implements ExchangeRate<T, F> {

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

}
