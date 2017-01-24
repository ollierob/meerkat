package net.meerkat.money.currency;

import java.util.Optional;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface HasCurrencyId extends HasCurrencyIds {

    @Nonnull
    CurrencyId currencyId();

    @Override
    default <T extends CurrencyId> Optional<T> currencyId(final Class<T> clazz) {
        return this.currencyId().cast(clazz);
    }

    @Override
    default CurrencyIds currencyIds() {
        return CurrencyIds.of(this.currencyId());
    }

}
