package net.ollie.meerkat.calculate.price.repo;

import java.time.LocalDate;

import javax.annotation.Nonnull;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.security.repo.Repo;

/**
 *
 * @author ollie
 */
public interface RepoPriceHandler {

    @Nonnull
    default <C extends CurrencyId> RepoPrice<C> price(
            final LocalDate date,
            final Repo<?> repo,
            final RepoShifts shifts,
            final C currency) {
        return repo.handleWith(this.priceContext(date, currency, shifts));
    }

    <C extends CurrencyId> RepoPriceContext<C> priceContext(LocalDate date, C currency, RepoShifts shifts);

    interface RepoPriceContext<C extends CurrencyId> extends Repo.Handler<RepoPrice<C>> {

    }

}
