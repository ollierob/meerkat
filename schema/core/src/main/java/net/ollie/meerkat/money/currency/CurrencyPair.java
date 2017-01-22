package net.ollie.meerkat.money.currency;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface CurrencyPair<B extends Currency, C extends Currency> extends HasCurrencies {

    @Nonnull
    B base();

    @Nonnull
    C counter();

    interface Untyped extends CurrencyPair<Currency, Currency> {

    }

}
