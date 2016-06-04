package net.ollie.meerkat.calculate.price.repo;

import java.time.temporal.Temporal;

import net.ollie.goat.money.currency.Currency;
import net.ollie.meerkat.calculate.price.SecurityPriceException;
import net.ollie.meerkat.calculate.price.SecurityTypePriceCalculator;
import net.ollie.meerkat.calculate.price.shifts.ExchangeRateShifts.ExchangeRateShifter;
import net.ollie.meerkat.security.repo.Repo;

/**
 *
 * @author ollie
 */
public interface RepoTypePricer<T extends Temporal, R extends Repo<?>>
        extends SecurityTypePriceCalculator<T, R>, ExchangeRateShifter {

    @Override
    <C extends Currency> RepoPrice.Shiftable<C> price(T valuation, R security, C currency)
            throws RepoPriceException;

    class RepoPriceException extends SecurityPriceException {

        private static final long serialVersionUID = 1L;

        public RepoPriceException(final String message) {
            super(message);
        }

        public RepoPriceException(final String message, final Exception cause) {
            super(message, cause);
        }

    }

}
