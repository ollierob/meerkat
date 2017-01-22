package net.ollie.meerkat.money.fx;

import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.money.currency.Currency;
import net.ollie.goat.numeric.fraction.DecimalFraction;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class TriangulatedExchangeRate<F extends Currency, X extends Currency, T extends Currency>
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
