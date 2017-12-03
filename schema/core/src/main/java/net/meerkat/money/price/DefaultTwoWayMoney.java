package net.meerkat.money.price;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.objects.Require;

/**
 *
 * @author Ollie
 */
public class DefaultTwoWayMoney<C extends CurrencyId> implements TwoWayMoney<C> {

    private final Money<C> bid, offer;

    public DefaultTwoWayMoney(final Money<C> bid, final Money<C> offer) {
        Require.argumentsEqual(bid.currencyId(), offer.currencyId());
        this.bid = bid;
        this.offer = offer;
    }

    @Override
    public DefaultTwoWayMoney<C> evaluate() {
        return this;
    }

    @Override
    public Money<C> mid() {
        throw new UnsupportedOperationException(); //TODO
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
    public C currencyId() {
        return bid.currencyId();
    }

}
