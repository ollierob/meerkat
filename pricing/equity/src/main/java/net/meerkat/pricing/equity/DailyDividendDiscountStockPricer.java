package net.meerkat.pricing.equity;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.instrument.equity.Stock;
import net.meerkat.pricing.InstrumentPriceException;
import net.meerkat.pricing.shifts.InstrumentPriceShifts;

import java.time.LocalDate;

public class DailyDividendDiscountStockPricer implements StockPricer<LocalDate> {

    @Override
    public <C extends CurrencyId> EquityPrice.Shiftable<C> price(
            final LocalDate date,
            final Stock instrument,
            final C currency,
            final InstrumentPriceShifts shifts)
            throws InstrumentPriceException {
        throw new UnsupportedOperationException();
    }

}
