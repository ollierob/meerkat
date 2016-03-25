package net.ollie.meerkat.calculate.price;

import javax.annotation.Nonnull;

import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author ollie
 */
public interface SecurityPrice {

    @Nonnull
    Money cleanPrice();

    @Nonnull
    Money dirtyPrice();

}
