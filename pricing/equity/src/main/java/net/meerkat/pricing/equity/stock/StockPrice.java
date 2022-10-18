package net.meerkat.pricing.equity.stock;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.pricing.equity.EquityPrice;
import net.meerkat.pricing.shifts.InstrumentPriceShifts;

public interface StockPrice<C extends CurrencyId>
        extends EquityPrice<C> {

    interface Shiftable<C extends CurrencyId> extends StockPrice<C>, EquityPrice.Shiftable<C> {

        @Override
        default StockPrice.Shiftable<C> shift(final InstrumentPriceShifts shifts) {
            return this.shift(StockPriceShifts.wrap(shifts));
        }

        StockPrice.Shiftable<C> shift(StockPriceShifts shifts);

    }

}
