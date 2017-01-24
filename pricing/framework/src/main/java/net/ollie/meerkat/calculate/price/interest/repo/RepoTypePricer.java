package net.ollie.meerkat.calculate.price.interest.repo;

import java.time.temporal.Temporal;

import net.ollie.meerkat.calculate.price.InstrumentPriceException;
import net.ollie.meerkat.calculate.price.shifts.ExchangeRateShifts.ExchangeRateShifter;
import net.meerkat.instrument.interest.repo.Repo;
import net.meerkat.money.currency.CurrencyId;
import net.ollie.meerkat.calculate.price.InstrumentTypePricer;

/**
 *
 * @author ollie
 */
public interface RepoTypePricer<T extends Temporal, R extends Repo<?>>
        extends InstrumentTypePricer<T, R>, ExchangeRateShifter {

    @Override
    <C extends CurrencyId> RepoPrice.Shiftable<C> price(T valuation, R security, C currency)
            throws RepoPriceException;

    class RepoPriceException extends InstrumentPriceException {

        private static final long serialVersionUID = 1L;

        public RepoPriceException(final String message) {
            super(message);
        }

        public RepoPriceException(final String message, final Exception cause) {
            super(message, cause);
        }

    }

}
