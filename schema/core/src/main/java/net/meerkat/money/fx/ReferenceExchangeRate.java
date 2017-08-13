package net.meerkat.money.fx;

import net.meerkat.identifier.currency.CurrencyId;
import net.ollie.goat.numeric.fraction.DecimalFraction;

/**
 *
 * @author ollie
 */
public class ReferenceExchangeRate<F extends CurrencyId, T extends CurrencyId>
        implements ExchangeRate<F, T> {

    private final F from;
    private final T to;
    private final DecimalFraction rate;

    public ReferenceExchangeRate(F from, T to, DecimalFraction rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
    }

    @Override
    public F from() {
        return from;
    }

    @Override
    public T to() {
        return to;
    }

    @Override
    public DecimalFraction rate() {
        return rate;
    }

}
