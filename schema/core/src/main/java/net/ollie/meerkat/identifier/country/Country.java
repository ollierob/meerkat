package net.ollie.meerkat.identifier.country;

import javax.annotation.Nonnull;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.utils.HasName;

/**
 *
 * @author Ollie
 */
public interface Country extends HasName {

    @Nonnull
    CurrencyId currency();

}
