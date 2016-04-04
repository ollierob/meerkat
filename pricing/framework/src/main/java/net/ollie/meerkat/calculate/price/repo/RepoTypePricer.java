package net.ollie.meerkat.calculate.price.repo;

import java.time.temporal.Temporal;

import net.ollie.meerkat.calculate.price.shifts.ExchangeRateShifts.ExchangeRateShifter;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.repo.Repo;
import net.ollie.meerkat.calculate.price.SecurityTypePricer;

/**
 *
 * @author ollie
 */
public interface RepoTypePricer<T extends Temporal, R extends Repo<?>>
        extends SecurityTypePricer<T, R>, ExchangeRateShifter {

    @Override
    <C extends CurrencyId> RepoPrice<C> price(T valuation, R security, C currency);

}
