package net.meerkat.identifier.security;

import javax.annotation.Nonnull;

import net.meerkat.identifier.InstrumentInMarketId;
import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.identifier.market.HasMarketId;
import net.meerkat.identifier.market.Mic;

/**
 *
 * @author ollie
 */
public class StockTicker
        implements InstrumentInMarketId, InstrumentId, HasMarketId {

    private final Mic mic;
    private final String ticker;

    public StockTicker(final Mic mic, final String ticker) {
        this.mic = mic;
        this.ticker = ticker;
    }

    @Override
    public Mic marketId() {
        return mic;
    }

    @Nonnull
    public String ticker() {
        return ticker;
    }

    @Override
    @Deprecated
    public InstrumentIds instrumentIds() {
        return InstrumentId.super.instrumentIds();
    }

    @Override
    public String toString() {
        return mic + ":" + this.ticker();
    }

}
