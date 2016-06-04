package net.ollie.meerkat.calculate.price.repo;

import java.time.temporal.Temporal;

import javax.annotation.Nonnull;

import net.ollie.goat.currency.CurrencyId;
import net.ollie.meerkat.security.repo.Repo;

/**
 *
 * @author ollie
 */
public interface RepoPricer<T extends Temporal> {

    @Nonnull
    default <C extends CurrencyId> RepoPrice.Shiftable<C> price(
            final T temporal,
            final Repo<?> repo,
            final C currency) {
        return repo.handleWith(this.priceContext(temporal, currency));
    }

    @Nonnull
    default <C extends CurrencyId> RepoPrice<C> price(
            final T temporal,
            final Repo<?> repo,
            final C currency,
            final RepoShifts shifts) {
        return this.price(temporal, repo, currency).shift(shifts);
    }

    <C extends CurrencyId> RepoPriceContext<C> priceContext(T date, C currency);

    interface RepoPriceContext<C extends CurrencyId> extends Repo.Handler<RepoPrice.Shiftable<C>> {

    }

}
