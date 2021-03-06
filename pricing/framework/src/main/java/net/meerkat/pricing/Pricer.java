package net.meerkat.pricing;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.exception.InstrumentException;
import net.meerkat.money.price.Price;

/**
 *
 * @author ollie
 */
public interface Pricer<T, R> {

    <C extends CurrencyId> Price<C> price(T temporal, R priced, C currency)
            throws InstrumentException, InstrumentPriceException;

}
