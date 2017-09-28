package net.meerkat.identifier;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.identifier.market.MarketId;

/**
 *
 * @author ollie
 */
public interface InstrumentInMarketId extends HasInstrumentInMarketId {

    @Override
    InstrumentIds instrumentIds();

    @Override
    MarketId marketId();

    @Override
    @Deprecated
    default InstrumentInMarketId instrumentInMarketId() {
        return this;
    }

}
