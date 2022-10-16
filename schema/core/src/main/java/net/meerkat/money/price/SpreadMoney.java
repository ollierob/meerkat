package net.meerkat.money.price;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;

/**
 * Two-way-money with some spread.
 *
 * @author ollie
 */
public record SpreadMoney<C extends CurrencyId>(Money<C> mid, Money<C> halfSpread) implements TwoWayMoney<C> {

    @Override
    public Money<C> bid() {
        return mid.minus(halfSpread);
    }

    @Override
    public Money<C> offer() {
        return mid.plus(halfSpread);
    }

    @Override
    public Money<C> spread() {
        return halfSpread.times(2);
    }

    @Override
    @Deprecated
    public SpreadMoney<C> evaluate() {
        return this;
    }

    @Override
    public C currencyId() {
        return mid.currencyId();
    }

}
