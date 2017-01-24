package net.ollie.meerkat.calculate.price.bond;

import java.time.temporal.Temporal;

import net.meerkat.instrument.bond.Bond;
import net.ollie.meerkat.calculate.price.InstrumentPriceException;
import net.meerkat.money.currency.CurrencyId;
import net.ollie.meerkat.calculate.price.InstrumentTypePricer;

/**
 * Prices particular types of bond.
 *
 * @author ollie
 * @see GenericBondPricer
 */
public interface BondTypePricer<T extends Temporal, B extends Bond>
        extends InstrumentTypePricer<T, B> {

    @Override
    default <C extends CurrencyId> BondPrice.Shiftable<C> price(T temporal, B bond, C currency) throws BondPriceException {
        return bond.handleWith(this.priceContext(temporal, currency));
    }

    default <C extends CurrencyId> BondPrice.Shiftable<C> price(final T temporal, final B bond, final C currency, final BondShifts shifts) throws BondPriceException {
        return this.price(temporal, bond, currency).shift(shifts);
    }

    <C extends CurrencyId> GenericBondPricer.BondPriceContext<C> priceContext(T valuation, C currency)
            throws BondPriceException;

    interface BondPriceContext<C extends CurrencyId> extends Bond.Handler<BondPrice.Shiftable<C>> {

    }

    class BondPriceException extends InstrumentPriceException {

        private static final long serialVersionUID = 1L;

        public BondPriceException(final String message) {
            super(message);
        }

    }

}
