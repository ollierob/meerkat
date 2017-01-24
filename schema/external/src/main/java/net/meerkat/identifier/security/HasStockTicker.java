package net.meerkat.identifier.security;

import java.util.Optional;

import net.meerkat.identifier.instrument.HasInstrumentIds;

/**
 *
 * @author Ollie
 */
public interface HasStockTicker extends HasInstrumentIds {

    default Optional<StockTicker> stockTicker() {
        return this.instrumentId(StockTicker.class);
    }

}
