package net.meerkat.pricing.equity;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.equity.Stock;
import net.meerkat.money.price.Price;
import net.meerkat.pricing.InstrumentPriceException;

/**
 *
 * @author Ollie
 */
public interface StockPricer<T> extends EquityPricer<T, Stock> {

    @Override
    <C extends CurrencyId> Price.Valued<C> price(T temporal, Stock instrument, C currency) throws InstrumentPriceException;

}
