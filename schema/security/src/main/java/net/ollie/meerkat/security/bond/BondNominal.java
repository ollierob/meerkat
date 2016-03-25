package net.ollie.meerkat.security.bond;

import javax.annotation.Nonnull;

import net.ollie.meerkat.numeric.money.Money;

/**
 *
 * @author ollie
 */
public interface BondNominal {

    @Nonnull
    Money<?> par();

}
