package net.meerkat.calculate.price.moneymarket;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.InstrumentException;
import net.meerkat.instrument.moneymarket.Repo;
import net.meerkat.pricing.InstrumentPricer;

/**
 *
 * @author ollie
 */
public interface RepoPricer extends InstrumentPricer<Repo<?>> {

    @Override
    <C extends CurrencyId> RepoPrice.Shiftable<C> price(Repo<?> repo, C currency) throws InstrumentException;

}
