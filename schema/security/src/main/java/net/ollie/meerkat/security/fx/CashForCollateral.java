package net.ollie.meerkat.security.fx;

import javax.annotation.Nonnull;

import net.ollie.meerkat.money.Money;
import net.ollie.meerkat.security.Security;

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
