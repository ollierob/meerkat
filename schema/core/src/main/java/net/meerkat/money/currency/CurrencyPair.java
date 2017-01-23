package net.meerkat.money.currency;

import java.util.Set;

import javax.annotation.Nonnull;

import net.ollie.goat.collection.Sets;

/**
 *
 * @author ollie
 */
public interface CurrencyPair<B extends CurrencyId, C extends CurrencyId> extends HasCurrencies {

    @Nonnull
    B base();

    @Nonnull
    C counter();

    @Override
    default Set<? extends CurrencyId> currencies() {
        return Sets.asSet(this.base(), this.counter());
    }

    interface Untyped extends CurrencyPair<CurrencyId, CurrencyId> {

    }

}
