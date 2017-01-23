package net.meerkat.identifier.country;

import javax.annotation.Nonnull;

import net.meerkat.utils.HasName;
import net.meerkat.money.currency.CurrencyId;

/**
 *
 * @author Ollie
 */
public interface Country extends HasName, HasCountryId {

    @Nonnull
    CurrencyId currency();

}
