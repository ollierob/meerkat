package net.meerkat.money.price;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;

/**
 *
 * @author Ollie
 */
public class TwoWayMoney<C extends CurrencyId> implements TwoWayPrice<C> {

    private final Money<C> bid;
    private final Money<C> offer;

    public TwoWayMoney(final Money<C> bid, final Money<C> offer) {
        this.bid = bid;
        this.offer = offer;
    }

    @Override
    public Money<C> bid() {
        return bid;
    }

    @Override
    public Money<C> offer() {
        return offer;
    }

    @Override
    @Deprecated
    public TwoWayMoney<C> evaluate() {
        return this;
    }

}
