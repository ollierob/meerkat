package net.meerkat.pricing.equity.stock;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.equity.Stock;
import net.meerkat.pricing.InstrumentPriceException;
import net.meerkat.pricing.equity.EquityPricer;
import net.meerkat.pricing.shifts.InstrumentPriceShifts;

/**
 *
 * @author Ollie
 * @see <a href="https://en.wikipedia.org/wiki/Stock_valuation">Stock valuation</a>
 */
public interface StockPricer<T> extends EquityPricer<T, Stock> {

    @Override
    <C extends CurrencyId> StockPrice.Shiftable<C> price(T temporal, Stock instrument, C currency, InstrumentPriceShifts shifts) throws InstrumentPriceException;

    @Override
    default <C extends CurrencyId> StockPrice.Shiftable<C> price(T temporal, Stock instrument, C currency) throws InstrumentPriceException {
        return this.price(temporal, instrument, currency, InstrumentPriceShifts.none());
    }

}
