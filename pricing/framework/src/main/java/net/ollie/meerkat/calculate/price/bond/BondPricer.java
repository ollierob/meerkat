package net.ollie.meerkat.calculate.price.bond;

import java.time.temporal.Temporal;

import javax.annotation.Nonnull;

import net.ollie.meerkat.calculate.price.SecurityTypePricer.PriceException;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.bond.Bond;

/**
 *
 * @author Ollie
 */
public interface BondPricer<T extends Temporal> {

    @Nonnull
    default <C extends CurrencyId> BondPrice.Shiftable<C> price(
            final T valuation,
            final Bond bond,
            final C currency)
            throws BondPriceException {
        return bond.handleWith(this.priceContext(valuation, currency));
    }

    @Nonnull
    default <C extends CurrencyId> BondPrice.Shiftable<C> price(
            final T valuation,
            final Bond bond,
            final C currency,
            final BondShifts shifts)
            throws BondPriceException {
        return this.price(valuation, bond, currency).shift(shifts);
    }

    <C extends CurrencyId> BondPriceContext<C> priceContext(T valuation, C currency)
            throws BondPriceException;

    interface BondPriceContext<C extends CurrencyId> extends Bond.Handler<BondPrice.Shiftable<C>> {

    }

    class BondPriceException extends PriceException {

        private static final long serialVersionUID = 1L;

        public BondPriceException(final String message) {
            super(message);
        }

    }

}
