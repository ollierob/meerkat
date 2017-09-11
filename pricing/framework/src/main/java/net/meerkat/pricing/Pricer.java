package net.meerkat.pricing;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.InstrumentException;
import net.meerkat.money.price.Price;

/**
 *
 * @author ollie
 */
public interface Pricer<X> {

    <C extends CurrencyId> Price<C> price(X toPrice, C currency) throws InstrumentException;

}
