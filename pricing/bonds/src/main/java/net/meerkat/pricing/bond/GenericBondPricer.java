package net.meerkat.pricing.bond;

import java.time.temporal.Temporal;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.bond.Bond;

/**
 * Tries to price any type of bond by considering the coupon payments.
 *
 * @see BondPricer for pricing specific types of bond.
 * @author Ollie
 */
public interface GenericBondPricer<T extends Temporal>
        extends BondPricer<T, Bond> {

    @Override
    default <C extends CurrencyId> BondPrice.Shiftable<C> price(
            final T temporal,
            final Bond bond,
            final C currency,
            final BondShifts shifts)
            throws BondPriceException {
        return bond.handleWith(this.priceContext(temporal, currency, shifts));
    }

    <C extends CurrencyId> BondPriceContext<C> priceContext(T valuation, C currency, BondShifts shifts)
            throws BondPriceException;

    interface BondPriceContext<C extends CurrencyId> extends Bond.Handler<BondPrice.Shiftable<C>> {

    }

}
