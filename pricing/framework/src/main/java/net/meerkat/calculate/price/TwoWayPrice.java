package net.meerkat.calculate.price;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public interface TwoWayPrice<C extends CurrencyId> extends Price<C> {

    @Nonnull
    Money<C> bid();

    @Nonnull
    Money<C> offer();

    default Money<C> spread() {
        return offer().minus(bid());
    }

    default Money<C> mid() {
        return bid().plus(offer()).over(2);
    }

}
