package net.meerkat.pricing.bond;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.bond.Bond;
import net.meerkat.pricing.InstrumentPricer;
import net.meerkat.pricing.bond.shifts.BondPriceShifts;
import net.meerkat.pricing.shifts.InstrumentPriceShifts;

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
    default <C extends CurrencyId> BondPrice.Shiftable<C> price(final T temporal, final B bond, final C currency) throws BondPriceException {
        return this.price(temporal, bond, currency, BondPriceShifts.none());
    }

    @Override
    default <C extends CurrencyId> BondPrice.Shiftable<C> price(T temporal, B bond, C currency, InstrumentPriceShifts shifts) throws BondPriceException {
        return this.price(temporal, bond, currency, BondPriceShifts.cast(shifts));
    }

    <C extends CurrencyId> BondPrice.Shiftable<C> price(T temporal, B bond, C currency, BondPriceShifts shifts) throws BondPriceException;

}
