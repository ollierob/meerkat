package net.meerkat.security;

import javax.annotation.Nonnull;

import net.meerkat.money.Money;

/**
 *
 * @author ollie
 */
public interface CashForCollateral<C extends Security> extends Security {

    @Nonnull
    Money<?> principal();

    @Nonnull
    C collateral();

}
