package net.ollie.meerkat.calculate.price.bond;

import java.time.temporal.Temporal;

import net.meerkat.money.currency.Currency;
import net.ollie.meerkat.calculate.price.SecurityTypePriceCalculator;
import net.ollie.meerkat.calculate.price.bond.BondPricer.BondPriceException;
import net.meerkat.security.bond.Bond;

/**
 *
 * @author ollie
 */
public interface BondTypePricer<T extends Temporal, B extends Bond>
        extends SecurityTypePriceCalculator<T, B> {

    @Override
    <C extends Currency> BondPrice.Shiftable<C> price(T temporal, B bond, C currency)
            throws BondPriceException;

}
