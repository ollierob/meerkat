package net.meerkat.calculate.price.moneymarket;

import net.meerkat.pricing.InstrumentPriceException;
import net.meerkat.pricing.InstrumentPricer;
import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.InstrumentException;
import net.meerkat.instrument.moneymarket.Repo;

/**
 *
 * @author ollie
 */
public interface RepoPricer<T>
        extends InstrumentPricer<T, Repo<?>> {

    @Override
    <C extends CurrencyId> RepoPrice.Shiftable<C> price(T temporal, Repo<?> repo, C currency)
            throws InstrumentException, InstrumentPriceException;

}
