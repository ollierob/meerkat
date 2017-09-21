package net.meerkat.identifier.currency;

import net.meerkat.instrument.Instrument;

/**
 *
 * @author ollie
 */
public interface CurrencyPair extends Instrument, HasCurrencyIds {

    Currency base();

    Currency counter();

    @Override
    default CurrencyIds currencyIds() {
        return this.base().currencyIds().union(this.counter().currencyIds());
    }

}
