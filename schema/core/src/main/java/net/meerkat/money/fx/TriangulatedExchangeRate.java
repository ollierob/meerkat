package net.meerkat.money.fx;

import java.util.Objects;

import net.meerkat.identifier.currency.CurrencyId;
import net.ollie.goat.numeric.fraction.BigDecimalFraction;

/**
 *
 * @author ollie
 */
public class TriangulatedExchangeRate<F extends CurrencyId, X extends CurrencyId, T extends CurrencyId>
        implements ExchangeRate<F, T> {

    private final ExchangeRate<F, X> first;
    private final ExchangeRate<X, T> second;

    public TriangulatedExchangeRate(final ExchangeRate<F, X> first, final ExchangeRate<X, T> second) {
        this.first = Objects.requireNonNull(first);
        this.second = Objects.requireNonNull(second);
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
    public BigDecimalFraction bidRate() {
        return first.bidRate().times(second.bidRate());
    }

    @Override
    public BigDecimalFraction offerRate() {
        return first.offerRate().times(second.offerRate());
    }

}
