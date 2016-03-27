package net.ollie.meerkat.calculate.price.repo;

import java.time.temporal.Temporal;

import net.ollie.meerkat.calculate.price.SecurityPricer;
import net.ollie.meerkat.calculate.price.shifts.ExchangeRateShifts.ExchangeRateShifter;
import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.repo.Repo;

/**
 *
 * @author ollie
 */
public interface RepoPricer<T extends Temporal, R extends Repo<?>>
        extends SecurityPricer<T, R>, ExchangeRateShifter {

    @Override
    default <C extends CurrencyId> RepoPrice<C> price(T temporal, R security, C currency) {
        return this.price(temporal, security, currency, RepoShifts.NONE);
    }

    <C extends CurrencyId> RepoPrice<C> price(T temporal, R security, C currency, RepoShifts shifts);

}
