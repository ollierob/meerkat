package net.meerkat.calculate.price.bond;

import net.meerkat.calculate.price.InstrumentPriceException;
import net.meerkat.calculate.price.InstrumentPricer;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.bond.Bond;

/**
 * Prices particular types of bond.
 *
 * @author ollie
 * @see GenericBondPricer
 * @param <B> bond type
 */
public interface BondPricer<T, B extends Bond>
        extends InstrumentPricer<T, B> {

    @Override
    <C extends CurrencyId> BondPrice.Shiftable<C> price(T temporal, B bond, C currency) throws BondPriceException;

    default <C extends CurrencyId> BondPrice.Shiftable<C> price(final T temporal, final B bond, final C currency, final BondShifts shifts) throws BondPriceException {
        return this.price(temporal, bond, currency).shift(shifts);
    }

    class BondPriceException extends InstrumentPriceException {

        private static final long serialVersionUID = 1L;

        public BondPriceException(final String message) {
            super(message);
        }

    }

}
