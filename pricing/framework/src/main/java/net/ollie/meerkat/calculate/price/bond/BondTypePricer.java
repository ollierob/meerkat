package net.ollie.meerkat.calculate.price.bond;

import java.time.temporal.Temporal;

import net.ollie.meerkat.calculate.price.bond.BondPricer.BondPriceException;
import net.ollie.goat.currency.CurrencyId;
import net.ollie.meerkat.security.bond.Bond;
import net.ollie.meerkat.calculate.price.SecurityTypePriceCalculator;

/**
 *
 * @author ollie
 */
public interface BondTypePricer<T extends Temporal, B extends Bond>
        extends SecurityTypePriceCalculator<T, B> {

    @Override
    <C extends CurrencyId> BondPrice.Shiftable<C> price(T temporal, B bond, C currency)
            throws BondPriceException;

}
