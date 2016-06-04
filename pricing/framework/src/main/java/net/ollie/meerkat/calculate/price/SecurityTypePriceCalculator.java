package net.ollie.meerkat.calculate.price;

import java.time.temporal.Temporal;

import net.ollie.goat.currency.Currency;
import net.ollie.meerkat.security.Security;

/**
 *
 * @author ollie
 */
public interface SecurityTypePriceCalculator<T extends Temporal, S extends Security> {

    <C extends Currency> SecurityPrice<C> price(T temporal, S security, C currency)
            throws SecurityException, SecurityPriceException;

}
