package net.meerkat.money.price;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public class SpreadMoney<C extends CurrencyId> implements TwoWayMoney<C> {

    private final Money<C> mid;
    private final Money<C> halfSpread;

    public SpreadMoney(final Money<C> mid, final Money<C> halfSpread) {
        this.mid = mid;
        this.halfSpread = halfSpread;
    }

    @Override
    public Money<C> bid() {
        return mid.minus(halfSpread);
    }

    @Override
    public Money<C> offer() {
        return mid.plus(halfSpread);
    }

    @Override
    public Money<C> mid() {
        return mid;
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
