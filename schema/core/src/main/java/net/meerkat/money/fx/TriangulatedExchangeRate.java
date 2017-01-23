package net.meerkat.money.fx;

import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.goat.numeric.fraction.DecimalFraction;
import net.meerkat.money.currency.CurrencyId;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class TriangulatedExchangeRate<F extends CurrencyId, X extends CurrencyId, T extends CurrencyId>
        implements ExchangeRate<F, T> {

    private ExchangeRate<F, X> first;
    private ExchangeRate<X, T> second;

    public TriangulatedExchangeRate(ExchangeRate<F, X> first, ExchangeRate<X, T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public F from() {
        return first.from();
    }

    @Override
    public T to() {
        return second.to();
    }

    @Override
    public DecimalFraction rate() {
        return first.rate().times(second.rate());
    }

}
