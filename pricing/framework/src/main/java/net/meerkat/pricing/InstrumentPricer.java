package net.meerkat.pricing;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.Instrument;
import net.meerkat.instrument.InstrumentException;
import net.meerkat.money.Price;

/**
 *
 * @author ollie
 */
public interface InstrumentPricer<T, I extends Instrument> extends Pricer<T, I> {

    @Override
    <C extends CurrencyId> Price<C> price(T temporal, I instrument, C currency) throws InstrumentException, InstrumentPriceException;

}
