package net.meerkat.instrument.equity;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.NamedInstrument;
import net.meerkat.issuer.IssuerId;

/**
 *
 * @author Ollie
 */
public class CommonStock
        extends NamedInstrument
        implements Equity {

    private final IssuerId issuer;

    public CommonStock(
            final String name,
            final InstrumentIds identifiers,
            final IssuerId issuer) {
        super(name, identifiers);
        this.issuer = issuer;
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
