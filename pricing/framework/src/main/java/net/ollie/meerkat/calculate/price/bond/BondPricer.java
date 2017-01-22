package net.ollie.meerkat.calculate.price.bond;

import java.time.temporal.Temporal;

import javax.annotation.Nonnull;

import net.meerkat.money.currency.Currency;
import net.ollie.meerkat.calculate.price.SecurityPriceException;
import net.meerkat.security.bond.Bond;

/**
 *
 * @author Ollie
 */
public interface BondPricer<T extends Temporal> extends BondTypePricer<T, Bond> {

    @Override
    default <C extends Currency> BondPrice.Shiftable<C> price(
            final T valuation,
            final Bond bond,
            final C currency)
            throws BondPriceException {
        return bond.handleWith(this.priceContext(valuation, currency));
    }

    <C extends Currency> BondPriceContext<C> priceContext(T valuation, C currency)
            throws BondPriceException;

    interface BondPriceContext<C extends Currency> extends Bond.Handler<BondPrice.Shiftable<C>> {

    }

    class BondPriceException extends SecurityPriceException {

        private static final long serialVersionUID = 1L;

        public BondPriceException(final String message) {
            super(message);
        }

    }

}
