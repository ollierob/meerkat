package net.meerkat.identifier.currency;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface HasCurrencyId extends HasCurrencyIds {

    @Nonnull
    CurrencyId currencyId();

    @Override
    default CurrencyIds currencyIds() {
        return CurrencyIds.of(this.currencyId());
    }

}
