package net.meerkat.pricing.bond;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.bond.Bond;
import net.meerkat.pricing.InstrumentPriceException;
import net.meerkat.pricing.InstrumentPricer;

/**
 * Prices particular types of bond.
 *
 * @author ollie
 * @see GenericBondPricer for pricing any type of bond.
 * @param <B> bond type
 */
public interface BondPricer<B extends Bond>
        extends InstrumentPricer<B> {

    @Override
    <C extends CurrencyId> BondPrice.Shiftable<C> price(B bond, C currency) throws BondPriceException;

    default <C extends CurrencyId> BondPrice.Shiftable<C> price(final B bond, final C currency, final BondShifts shifts) throws BondPriceException {
        return this.price(bond, currency).shift(shifts);
    }

    class BondPriceException extends InstrumentPriceException {

        private static final long serialVersionUID = 1L;

        public BondPriceException(final String message) {
            super(message);
        }

    }

}
