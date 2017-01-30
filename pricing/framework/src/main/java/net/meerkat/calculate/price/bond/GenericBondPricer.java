package net.meerkat.calculate.price.bond;

import java.time.temporal.Temporal;

import net.meerkat.instrument.bond.Bond;
import net.meerkat.identifier.currency.CurrencyId;

/**
 * Tries to price any type of bond by considering the coupon payments.
 *
 * @author Ollie
 */
public interface GenericBondPricer<T extends Temporal> extends BondPricer<T, Bond> {

    @Override
    default <C extends CurrencyId> BondPrice.Shiftable<C> price(
            final T temporal,
            final Bond bond,
            final C currency)
            throws BondPriceException {
        return bond.handleWith(this.priceContext(temporal, currency));
    }

    <C extends CurrencyId> GenericBondPricer.BondPriceContext<C> priceContext(T valuation, C currency)
            throws BondPriceException;

    interface BondPriceContext<C extends CurrencyId> extends Bond.Handler<BondPrice.Shiftable<C>> {

    }

}
