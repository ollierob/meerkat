package net.meerkat.pricing.moneymarket;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.InstrumentException;
import net.meerkat.instrument.repo.Repo;

/**
 *
 * @author Ollie
 */
public interface GenericRepoPricer<T> extends RepoPricer<T, Repo<?>> {

    @Override
    default <C extends CurrencyId> RepoPrice.Shiftable<C> price(
            final T temporal,
            final Repo<?> repo,
            final C currency)
            throws InstrumentException {
        return repo.handleWith(this.priceContext(temporal, currency));
    }

    <C extends CurrencyId> RepoPriceContext<T, C> priceContext(T valuation, C currency);

    interface RepoPriceContext<T, C extends CurrencyId> extends Repo.Handler<RepoPrice.Shiftable<C>> {

    }

}
