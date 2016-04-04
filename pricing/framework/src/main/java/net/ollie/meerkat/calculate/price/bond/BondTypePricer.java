package net.ollie.meerkat.calculate.price.bond;

import java.time.temporal.Temporal;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.bond.Bond;
import net.ollie.meerkat.calculate.price.SecurityTypePricer;

/**
 *
 * @author ollie
 */
public interface BondTypePricer<T extends Temporal, B extends Bond>
        extends SecurityTypePricer<T, B> {

    @Override
    <C extends CurrencyId> BondPrice<C> price(T temporal, B bond, C currency);

}
