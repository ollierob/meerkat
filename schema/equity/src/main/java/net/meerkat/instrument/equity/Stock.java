package net.meerkat.instrument.equity;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.IssuedSecurity;
import net.meerkat.issuer.IssuerId;

/**
 *
 * @author ollie
 */
public class Stock extends IssuedSecurity implements Equity {

    private final boolean preferred;

    public Stock(
            final String name,
            final InstrumentIds identifiers,
            final IssuerId issuerId,
            final boolean preferred) {
        super(name, identifiers, issuerId);
        this.preferred = preferred;
    }

    public boolean isPreferred() {
        return preferred;
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
