package net.meerkat.calculate.price.bond.future;

import java.time.temporal.Temporal;

import net.meerkat.calculate.price.InstrumentPriceException;
import net.meerkat.instrument.interest.future.BondFuture;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.calculate.price.InstrumentTypePricer;

/**
 *
 * @author Ollie
 */
public interface BondFuturePricer<T extends Temporal>
        extends InstrumentTypePricer<T, BondFuture> {

    @Override
    <C extends CurrencyId> BondFuturePrice.Shiftable<C> price(T temporal, BondFuture future, C currency)
            throws BondFuturePriceException;

    class BondFuturePriceException extends InstrumentPriceException {

        private static final long serialVersionUID = 1L;

        public BondFuturePriceException(final String message) {
            super(message);
        }

        public BondFuturePriceException(final String message, final Exception cause) {
            super(message, cause);
        }

    }

}
