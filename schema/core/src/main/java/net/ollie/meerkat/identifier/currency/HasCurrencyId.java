package net.ollie.meerkat.identifier.currency;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface HasCurrencyId {

    @Nonnull
    CurrencyId currency();

}
