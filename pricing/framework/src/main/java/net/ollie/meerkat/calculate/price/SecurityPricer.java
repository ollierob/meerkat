package net.ollie.meerkat.calculate.price;

import java.time.temporal.Temporal;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.Security;

/**
 *
 * @author ollie
 */
public interface SecurityPricer<T extends Temporal, S extends Security> {

    <C extends CurrencyId> SecurityPrice<C> price(T temporal, S security, C currency);

}
