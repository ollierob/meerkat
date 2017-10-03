package net.meerkat.money.price;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public interface TwoWayMoney<C extends CurrencyId> extends TwoWayPrice<Money<C>, C> {

    default Money<C> spread() {
        return this.offer().minus(this.bid());
    }

    @Override
    default boolean isCrossed() {
        return this.spread().isNegative();
    }

    @Override
    default boolean hasSpread() {
        return this.spread().isZero();
    }

    @Override
    TwoWayMoney<C> evaluate();

    static <C extends CurrencyId> TwoWayMoney<C> of(final Money<C> bid, final Money<C> offer) {
        throw new UnsupportedOperationException();
    }

}
