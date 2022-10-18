package net.meerkat.pricing.equity.stock;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.ollie.goat.data.BiProvider;

public interface StockPriceProvider<T> extends BiProvider<T, InstrumentIds, StockPrice<?>> {

}
