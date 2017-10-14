package net.meerkat.money.price;

import java.math.BigDecimal;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;

/**
 *
 * @author Ollie
 */
public class TwoWayDecimalMoney<C extends CurrencyId> implements TwoWayMoney<C> {

    private final C currencyId;
    private final BigDecimal bid, offer;

    public TwoWayDecimalMoney(final C currencyId, final BigDecimal bid, final BigDecimal offer) {
        this.currencyId = currencyId;
        this.bid = bid;
        this.offer = offer;
    }

    @Override
    public Money<C> bid() {
        return Money.of(currencyId, bid);
    }

    @Override
    public Money<C> offer() {
        return Money.of(currencyId, offer);
    }

    @Override
    public Money<C> mid() {
        throw new UnsupportedOperationException(); //TODO
    }

    @Override
    public C currencyId() {
        return currencyId;
    }

}
