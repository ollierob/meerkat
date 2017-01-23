package net.ollie.meerkat.calculate.price.bond;

import java.time.temporal.Temporal;

import net.meerkat.money.currency.Currency;
import net.meerkat.instrument.bond.Bond;

/**
 * Tries to price any type of bond by considering the coupon payments.
 *
 * @author Ollie
 */
public interface GenericBondPricer<T extends Temporal> extends BondPricer<T, Bond> {

    @Override
    default <C extends Currency> BondPrice.Shiftable<C> price(
            final T temporal,
            final Bond bond,
            final C currency)
            throws BondPriceException {
        return bond.handleWith(this.priceContext(temporal, currency));
    }

}
