package net.ollie.meerkat.calculate.price;

import java.time.temporal.Temporal;

import net.ollie.meerkat.security.Security;

/**
 *
 * @author ollie
 */
public interface SecurityPricer<T extends Temporal, S extends Security> {

    SecurityPrice price(T temporal, S security);

}
