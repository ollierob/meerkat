package net.meerkat.instrument.equity;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.IssuedSecurity;
import net.meerkat.issuer.IssuerId;

/**
 *
 * @author Ollie
 */
public class CommonStock
        extends IssuedSecurity
        implements Stock {

    public CommonStock(final String name, final InstrumentIds identifiers, final IssuerId issuerId) {
        super(name, identifiers, issuerId);
    }

    @Override
    public <R> R handleWith(final Equity.Handler<R> handler) {
        return handler.handle(this);
    }

}
