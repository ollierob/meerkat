package net.meerkat.identifier.currency;

import javax.annotation.Nonnull;

import net.meerkat.instrument.Instrument;

/**
 *
 * @author ollie
 */
public interface CurrencyPair extends Instrument, HasCurrencyIds {

    @Nonnull
    Currency base();

    @Nonnull
    Currency counter();

    @Override
    default CurrencyIds currencyIds() {
        return this.base().currencyIds().union(this.counter().currencyIds());
    }

}
