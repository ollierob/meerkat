package net.ollie.meerkat.calculate.price.repo;

import java.time.temporal.Temporal;

import javax.annotation.Nonnull;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.repo.Repo;

/**
 *
 * @author ollie
 */
public interface RepoPricer<T extends Temporal> {

    @Nonnull
    default <C extends CurrencyId> RepoPrice<C> price(
            final T temporal,
            final Repo<?> repo,
            final RepoShifts shifts,
            final C currency) {
        return repo.handleWith(this.priceContext(temporal, currency, shifts));
    }

    <C extends CurrencyId> RepoPriceContext<C> priceContext(T date, C currency, RepoShifts shifts);

    interface RepoPriceContext<C extends CurrencyId> extends Repo.Handler<RepoPrice<C>> {

    }

}
