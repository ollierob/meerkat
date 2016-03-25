package net.ollie.meerkat.calculate.price;

import javax.annotation.Nonnull;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author ollie
 */
public interface SecurityPrice<C extends CurrencyId> {

    @Nonnull
    Money<C> cleanPrice();

    @Nonnull
    Money<C> dirtyPrice();

}
