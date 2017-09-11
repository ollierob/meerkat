package net.meerkat.pricing;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.Instrument;
import net.meerkat.instrument.InstrumentException;
import net.meerkat.money.price.Price;

/**
 *
 * @author ollie
 */
public interface InstrumentPricer<I extends Instrument> extends Pricer<I> {

    @Override
    <C extends CurrencyId> Price<C> price(I instrument, C currency) throws InstrumentException;

}
