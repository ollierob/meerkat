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
    Currency currency();

    @Override
    default Set<? extends Currency> currencies() {
        return Collections.singleton(this.currency());
    }

}
