package net.meerkat.money.fx;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.ollie.goat.numeric.fraction.BigDecimalFraction;

/**
 *
 * @author Ollie
 */
public class RatioExchangeRate<F extends CurrencyId, T extends CurrencyId> implements ExchangeRate<F, T> {

    private final Money<F> from;
    private final Money<T> to;

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
    public BigDecimalFraction bidRate() {
        return this.midRate();
    }

    @Override
    public BigDecimalFraction offerRate() {
        return this.midRate();
    }

    @Override
    public BigDecimalFraction midRate() {
        return BigDecimalFraction.of(to.amount(), from.amount());
    }

}
