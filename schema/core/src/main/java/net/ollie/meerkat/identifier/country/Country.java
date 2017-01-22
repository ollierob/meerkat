package net.ollie.meerkat.identifier.country;

import javax.annotation.Nonnull;

import net.ollie.meerkat.money.currency.Currency;
import net.ollie.meerkat.utils.HasName;

/**
 *
 * @author Ollie
 */
public interface Country extends HasName {

    @Nonnull
    Currency currency();

}
