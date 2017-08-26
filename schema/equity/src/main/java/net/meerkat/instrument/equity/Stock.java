package net.meerkat.instrument.equity;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.NamedInstrument;
import net.meerkat.issuer.IssuerId;

/**
 *
 * @author Ollie
 */
public class Stock
        extends NamedInstrument
        implements Equity {

    private static final long serialVersionUID = 1L;

    private final boolean preferred;
    private final IssuerId issuer;

    public Stock(
            final String name,
            final InstrumentIds identifiers,
            final boolean preferred,
            final IssuerId issuer) {
        super(name, identifiers);
        this.preferred = preferred;
        this.issuer = issuer;
    }

    public boolean isPreferred() {
        return preferred;
    }

    @Override
    public IssuerId issuerId() {
        return issuer;
    }

    @Override
    public <R> R handleWith(final Equity.Handler<R> handler) {
        return handler.handle(this);
    }

}
