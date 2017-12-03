package net.meerkat.money.fx;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.numeric.fraction.BigDecimalFraction;
import net.meerkat.objects.Require;

/**
 *
 * @author ollie
 */
public class ReferenceExchangeRate<F extends CurrencyId, T extends CurrencyId>
        implements ExchangeRate<F, T> {

    public static <F extends CurrencyId, T extends CurrencyId> ReferenceExchangeRate<F, T> ofMid(final F from, final T to, final Number midRate) {
        final BigDecimalFraction mid = BigDecimalFraction.of(midRate);
        return new ReferenceExchangeRate<>(from, to, mid, mid);
    }

    private final F from;
    private final T to;
    private final BigDecimalFraction bidRate;
    private final BigDecimalFraction offerRate;

    protected ReferenceExchangeRate(final F from, final T to, final BigDecimalFraction bidRate, final BigDecimalFraction offerRate) {
        Require.argument(bidRate.isPositive(), "Must have a positive bid rate!");
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
    public BigDecimalFraction bidRate() {
        return bidRate;
    }

    @Override
    public BigDecimalFraction offerRate() {
        return offerRate;
    }

}
