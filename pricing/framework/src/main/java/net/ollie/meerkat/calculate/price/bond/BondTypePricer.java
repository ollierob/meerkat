package net.ollie.meerkat.calculate.price.bond;

import java.time.temporal.Temporal;

import net.ollie.meerkat.calculate.price.SecurityTypePricer;
import net.ollie.meerkat.calculate.price.bond.BondPricer.BondPriceException;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.bond.Bond;

/**
 *
 * @author ollie
 */
public interface BondTypePricer<T extends Temporal, B extends Bond>
        extends SecurityTypePricer<T, B> {

    @Override
    <C extends CurrencyId> BondPrice.Shiftable<C> price(T temporal, B bond, C currency)
            throws BondPriceException;

}
