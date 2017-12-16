package net.meerkat.pricing.equity.stock;

import net.meerkat.pricing.shifts.InstrumentPriceShifts;

public interface StockPriceShifts extends InstrumentPriceShifts {

    static StockPriceShifts wrap(final InstrumentPriceShifts shifts) {
        throw new UnsupportedOperationException(); //TODO
    }

}
