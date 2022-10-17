package net.meerkat.pricing.equity.stock;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.ollie.goat.data.BiProvider;

import java.time.Instant;

public interface StockPriceProvider extends BiProvider<Instant, InstrumentIds, StockPrice<?>> {

}
