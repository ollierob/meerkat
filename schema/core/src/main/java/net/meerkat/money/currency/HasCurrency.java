package net.meerkat.money.currency;

import java.util.Collections;
import java.util.Set;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface HasCurrency extends HasCurrencies {

    @Nonnull
    CurrencyId currency();

    @Override
    default Set<? extends CurrencyId> currencies() {
        return Collections.singleton(this.currency());
    }

}
