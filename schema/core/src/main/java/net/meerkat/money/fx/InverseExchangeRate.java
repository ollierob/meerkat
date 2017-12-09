package net.meerkat.money.fx;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.numeric.decimal.BigDecimalFraction;

/**
 *
 * @author Ollie
 */
public class InverseExchangeRate<T extends CurrencyId, F extends CurrencyId> implements ExchangeRate<T, F> {

    private final ExchangeRate<F, T> inverse;

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
    public BigDecimalFraction bidRate() {
        return inverse.bidRate().reciprocal();
    }

    @Override
    public BigDecimalFraction offerRate() {
        return inverse.offerRate().reciprocal();
    }

    @Override
    public ExchangeRate<F, T> inverse() {
        return inverse;
    }

}
