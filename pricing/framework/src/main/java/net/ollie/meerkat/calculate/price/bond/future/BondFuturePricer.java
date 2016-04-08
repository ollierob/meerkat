package net.ollie.meerkat.calculate.price.bond.future;

import java.time.temporal.Temporal;

import net.ollie.meerkat.calculate.price.SecurityTypePricer;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.bond.future.BondFuture;

/**
 *
 * @author Ollie
 */
public interface BondFuturePricer<T extends Temporal>
        extends SecurityTypePricer<T, BondFuture> {

    @Override
    <C extends CurrencyId> BondFuturePrice<C> price(T temporal, BondFuture future, C currency)
            throws BondFuturePriceException;

    class BondFuturePriceException extends PriceException {

        private static final long serialVersionUID = 1L;

        private final BondFuture future;

        public BondFuturePriceException(final String message, final BondFuture future) {
            super(message);
            this.future = future;
        }

        public BondFuturePriceException(final String message, final Exception cause, final BondFuture future) {
            super(message, cause);
            this.future = future;
        }

        public BondFuture future() {
            return future;
        }

    }

}
