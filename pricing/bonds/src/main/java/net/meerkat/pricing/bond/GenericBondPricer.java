package net.meerkat.pricing.bond;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.bond.Bond;

/**
 * Tries to price any type of bond by considering the coupon payments.
 *
 * @see BondPricer for pricing specific types of bond.
 * @author Ollie
 */
public interface GenericBondPricer
        extends BondPricer<Bond> {

    @Override
    default <C extends CurrencyId> BondPrice.Shiftable<C> price(
            final Bond bond,
            final C currency)
            throws BondPriceException {
        return bond.handleWith(this.priceContext(currency));
    }

    <C extends CurrencyId> BondPriceContext<C> priceContext(C currency) throws BondPriceException;

    interface BondPriceContext<C extends CurrencyId> extends Bond.Handler<BondPrice.Shiftable<C>> {

    }

}
