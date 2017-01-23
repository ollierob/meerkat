package net.meerkat.money.currency;

import java.util.Set;

import javax.annotation.Nonnull;

import net.ollie.goat.collection.Sets;

/**
 *
 * @author ollie
 */
public interface CurrencyPair<B extends Currency, C extends Currency> extends HasCurrencies {

    @Nonnull
    B base();

    @Nonnull
    C counter();

    @Override
    default Set<? extends Currency> currencies() {
        return Sets.asSet(this.base(), this.counter());
    }

    interface Untyped extends CurrencyPair<Currency, Currency> {

    }

}
