package net.meerkat.identifier.currency;

import net.meerkat.instrument.Instrument;

import javax.annotation.Nonnull;

/**
 * A pair of currencies.
 *
 * @author ollie
 * @see CurrencyIdPair
 */
public interface CurrencyPair<C extends Currency> extends Instrument, HasCurrencyIds {

    @Nonnull
    C base();

    @Nonnull
    C counter();

    @Override
    default CurrencyIds currencyIds() {
        return this.base().currencyIds().union(this.counter().currencyIds());
    }

}
