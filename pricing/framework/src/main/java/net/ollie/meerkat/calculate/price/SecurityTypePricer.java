package net.ollie.meerkat.calculate.price;

import java.time.temporal.Temporal;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.Security;

/**
 *
 * @author ollie
 */
public interface SecurityTypePricer<T extends Temporal, S extends Security> {

    <C extends CurrencyId> SecurityPrice<C> price(T temporal, S security, C currency) throws PriceException;

    class PriceException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public PriceException(final String message) {
            super(message);
        }

        public PriceException(final String message, final Exception cause) {
            super(message, cause);
        }

    }

}
