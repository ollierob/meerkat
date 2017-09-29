package net.meerkat.identifier.instrument;

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
