package net.meerkat.pricing.bond;

import net.meerkat.pricing.bond.shifts.BondShifts;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.bond.Bond;
import net.meerkat.pricing.InstrumentPricer;

/**
 * Prices particular types of bond.
 *
 * @author ollie
 * @see GenericBondPricer for pricing any type of bond.
 * @param <B> bond type
 */
public interface BondPricer<T, B extends Bond>
        extends InstrumentPricer<T, B> {

    @Override
    default <C extends CurrencyId> BondPrice.Shiftable<C> price(T temporal, B bond, C currency) throws BondPriceException {
        return this.price(temporal, bond, currency, BondShifts.none());
    }

    <C extends CurrencyId> BondPrice.Shiftable<C> price(T temporal, B bond, C currency, BondShifts shifts) throws BondPriceException;

}
