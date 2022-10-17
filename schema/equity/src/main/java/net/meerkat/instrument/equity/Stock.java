package net.meerkat.instrument.equity;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.issuer.IssuerId;

import javax.annotation.CheckForNull;

/**
 * @author ollie
 */
public class Stock extends AbstractEquity {

    private final boolean preferred;
    private final StockClass stockClass;

    public Stock(
            final String name,
            final InstrumentIds identifiers,
            final IssuerId issuerId,
            final boolean preferred,
            final StockClass stockClass) {
        super(name, identifiers, issuerId);
        this.preferred = preferred;
        this.stockClass = stockClass;
    }

    public boolean isPreferred() {
        return preferred;
    }

    public boolean isCommon() {
        return !preferred;
    }

    @CheckForNull
    public StockClass stockClass() {
        return stockClass;
    }

    @Override
    public <R> R handleWith(final Equity.Handler<R> handler) {
        return handler.handle(this);
    }

    @Override
    public ExplanationBuilder explain() {
        return super.explain().put("preferred", preferred);
    }

}
