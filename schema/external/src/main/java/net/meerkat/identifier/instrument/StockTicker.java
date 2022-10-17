package net.meerkat.identifier.instrument;

import net.meerkat.Explainable;
import net.meerkat.identifier.InstrumentInMarketId;
import net.meerkat.identifier.market.HasMarketId;
import net.meerkat.identifier.market.Mic;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * @author ollie
 */
public record StockTicker(Mic marketId, String ticker)
        implements InstrumentInMarketId, InstrumentId, HasMarketId, Explainable {

    @Override
    @Deprecated
    public InstrumentIds instrumentIds() {
        return InstrumentId.super.instrumentIds();
    }

    @Nonnull
    @Override
    public Map<String, Object> explain() {
        return this.explanationBuilder()
                .put("mic", marketId)
                .put("ticker", ticker);
    }

    @Override
    public String toString() {
        return marketId + ":" + this.ticker();
    }

}
