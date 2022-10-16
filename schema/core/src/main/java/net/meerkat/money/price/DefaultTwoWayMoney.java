package net.meerkat.money.price;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;
import net.meerkat.objects.Arguments;

import java.math.BigDecimal;

/**
 * @author Ollie
 */
public record DefaultTwoWayMoney<C extends CurrencyId>(Money<C> bid, Money<C> offer) implements TwoWayMoney<C> {

    public DefaultTwoWayMoney {
        Arguments.requireEqual(bid.currencyId(), offer.currencyId());
    }

    public DefaultTwoWayMoney(final C currency, final BigDecimal bid, final BigDecimal offer) {
        this(Money.of(currency, bid), Money.of(currency, offer));
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
