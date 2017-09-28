package net.meerkat.instrument.equity.index;

import javax.annotation.Nonnull;

import net.coljate.map.Map;
import net.coljate.set.ImmutableSet;
import net.coljate.set.Set;
import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.InstrumentDefinition;
import net.meerkat.instrument.NamedInstrument;
import net.meerkat.instrument.equity.Equity;
import net.meerkat.instrument.equity.EquityProvider;
import net.meerkat.instrument.equity.UnknownEquityException;

/**
 *
 * @author Ollie
 */
public class StockIndex extends NamedInstrument implements InstrumentDefinition {

    private final ImmutableSet<? extends InstrumentId> stockIds;

    public StockIndex(
            final String name,
            final InstrumentIds ids,
            final Set<? extends InstrumentId> stockIds) {
        super(name, ids);
        this.stockIds = stockIds.immutableCopy();
    }

    @Nonnull
    public ImmutableSet<? extends InstrumentId> constituentIds() {
        return stockIds;
    }

    @Nonnull
    public Map<InstrumentId, Equity> constituents(final EquityProvider equityProvider) throws UnknownEquityException {
        return equityProvider.requireAll(stockIds);
    }

    @Override
    public <R> R handleWith(final InstrumentDefinition.Handler<R> handler) {
        return handler instanceof Equity.Handler
                ? ((Equity.Handler<R>) handler).handle(this)
                : handler.handleUnknown(this);
    }

}
