package net.meerkat.calculate.price;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.Instrument;
import net.meerkat.instrument.InstrumentException;

/**
 *
 * @author ollie
 */
public interface InstrumentPricer<T, I extends Instrument> {

    <C extends CurrencyId> InstrumentPrice<C> price(T temporal, I instrument, C currency)
            throws InstrumentException, InstrumentPriceException;

}
