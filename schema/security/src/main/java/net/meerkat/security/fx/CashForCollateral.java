package net.meerkat.security.fx;

import javax.annotation.Nonnull;

import net.meerkat.money.Money;
import net.meerkat.security.Security;

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
