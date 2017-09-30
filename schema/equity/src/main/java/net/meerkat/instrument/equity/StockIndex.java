package net.meerkat.instrument.equity;

import javax.annotation.Nonnull;

import net.coljate.map.Map;
import net.coljate.set.ImmutableSet;
import net.coljate.set.Set;
import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.IssuedSecurity;
import net.meerkat.instrument.equity.exception.UnknownEquityException;
import net.meerkat.issuer.IssuerId;

/**
 *
 * @author Ollie
 */
public class StockIndex extends IssuedSecurity implements Equity {

    private final ImmutableSet<? extends InstrumentId> stockIds;

    public StockIndex(
            final String name,
            final InstrumentIds ids,
            final IssuerId issuerId,
            final Set<? extends InstrumentId> stockIds) {
        super(name, ids, issuerId);
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
    public <R> R handleWith(final Equity.Handler<R> handler) {
        return handler.handle(this);
    }

}
