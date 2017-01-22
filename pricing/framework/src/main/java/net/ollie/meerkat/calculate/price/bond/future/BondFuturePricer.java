package net.ollie.meerkat.calculate.price.bond.future;

import java.time.temporal.Temporal;

import net.ollie.meerkat.money.currency.Currency;
import net.ollie.meerkat.calculate.price.SecurityPriceException;
import net.ollie.meerkat.calculate.price.SecurityTypePriceCalculator;
import net.meerkat.security.interest.future.BondFuture;

/**
 *
 * @author Ollie
 */
public interface BondFuturePricer<T extends Temporal>
        extends SecurityTypePriceCalculator<T, BondFuture> {

    @Override
    <C extends Currency> BondFuturePrice.Shiftable<C> price(T temporal, BondFuture future, C currency)
            throws BondFuturePriceException;

    class BondFuturePriceException extends SecurityPriceException {

        private static final long serialVersionUID = 1L;

        public BondFuturePriceException(final String message) {
            super(message);
        }

        public BondFuturePriceException(final String message, final Exception cause) {
            super(message, cause);
        }

    }

}
