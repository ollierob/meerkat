package net.meerkat.pricing.moneymarket;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.exception.InstrumentException;
import net.meerkat.instrument.repo.Repo;
import net.meerkat.pricing.InstrumentPriceException;
import net.meerkat.pricing.InstrumentPricer;
import net.meerkat.pricing.shifts.InstrumentShifts;

/**
 *
 * @author ollie
 */
public interface RepoPricer<T, R extends Repo<?>> extends InstrumentPricer<T, R> {

    @Override
    <C extends CurrencyId> RepoPrice.Shiftable<C> price(T temporal, R repo, C currency, InstrumentShifts shifts) throws InstrumentException;

    @Override
    default <C extends CurrencyId> RepoPrice.Shiftable<C> price(T temporal, R instrument, C currency) throws InstrumentPriceException {
        return this.price(temporal, instrument, currency, InstrumentShifts.none());
    }

}
