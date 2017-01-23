package net.ollie.meerkat.calculate.price;

import java.time.temporal.Temporal;

import net.meerkat.instrument.Instrument;
import net.meerkat.money.currency.CurrencyId;

/**
 *
 * @author ollie
 */
public interface SecurityTypePriceCalculator<T extends Temporal, S extends Instrument> {

    <C extends CurrencyId> SecurityPrice<C> price(T temporal, S security, C currency)
            throws SecurityException, SecurityPriceException;

}
