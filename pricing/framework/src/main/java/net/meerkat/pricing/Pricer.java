package net.meerkat.pricing;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.InstrumentException;
import net.meerkat.money.Price;

/**
 *
 * @author ollie
 */
public interface Pricer<T, R> {

    <C extends CurrencyId> Price<C> price(T temporal, R toPrice, C currency)
            throws InstrumentException, InstrumentPriceException;

}
