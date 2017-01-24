package net.ollie.meerkat.calculate.price;

import java.time.temporal.Temporal;

import net.meerkat.instrument.Instrument;
import net.meerkat.money.currency.CurrencyId;

/**
 *
 * @author ollie
 */
public interface InstrumentTypePricer<T extends Temporal, S extends Instrument> {

    <C extends CurrencyId> InstrumentPrice<C> price(T temporal, S security, C currency)
            throws SecurityException, InstrumentPriceException;

}
