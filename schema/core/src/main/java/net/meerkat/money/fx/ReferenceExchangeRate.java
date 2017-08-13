package net.meerkat.money.fx;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.utils.Require;
import net.ollie.goat.numeric.fraction.DecimalFraction;

/**
 *
 * @author ollie
 */
public class ReferenceExchangeRate<F extends CurrencyId, T extends CurrencyId>
        implements ExchangeRate<F, T> {

    public static <F extends CurrencyId, T extends CurrencyId> ReferenceExchangeRate<F, T> ofMid(final F from, final T to, final Number midRate) {
        final DecimalFraction mid = DecimalFraction.of(midRate);
        return new ReferenceExchangeRate<>(from, to, mid, mid);
    }

    private final F from;
    private final T to;
    private final DecimalFraction bidRate;
    private final DecimalFraction offerRate;

    protected ReferenceExchangeRate(final F from, final T to, final DecimalFraction bidRate, final DecimalFraction offerRate) {
        Require.that(bidRate.isPositive(), "Must have a positive bid rate!");
        this.from = from;
        this.to = to;
        this.bidRate = bidRate;
        this.offerRate = offerRate;
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
    public DecimalFraction bidRate() {
        return bidRate;
    }

    @Override
    public DecimalFraction offerRate() {
        return offerRate;
    }

}
