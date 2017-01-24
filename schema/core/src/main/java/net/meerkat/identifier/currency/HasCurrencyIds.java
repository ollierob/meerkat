package net.meerkat.identifier.currency;

import java.util.Optional;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface HasCurrencyIds {

    @Nonnull
    CurrencyIds currencyIds();

    default <T extends CurrencyId> Optional<T> currencyId(final Class<T> clazz) {
        return this.currencyIds().id(clazz);
    }

}
