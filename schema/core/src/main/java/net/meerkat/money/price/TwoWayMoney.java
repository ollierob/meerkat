package net.meerkat.money.price;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public interface TwoWayMoney<C extends CurrencyId> extends TwoWayPrice<Money<C>, C> {

    @Override
    default boolean isCrossed() {
        return this.bid().minus(this.offer()).isZero();
    }

    @Override
    default boolean hasSpread() {
        return !this.offer().minus(this.bid()).isZero();
    }

    static <C extends CurrencyId> TwoWayMoney<C> of(final Money<C> bid, final Money<C> offer) {
        throw new UnsupportedOperationException();
    }

}
