package net.meerkat.pricing.moneymarket;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.InstrumentException;
import net.meerkat.instrument.repo.Repo;
import net.meerkat.pricing.InstrumentPricer;

/**
 *
 * @author ollie
 */
public interface RepoPricer<T, R extends Repo<?>> extends InstrumentPricer<T, R> {

    @Override
    <C extends CurrencyId> RepoPrice.Shiftable<C> price(T temporal, R repo, C currency) throws InstrumentException;

}
