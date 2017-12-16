package net.meerkat.instrument.equity;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.IssuedSecurity;
import net.meerkat.issuer.IssuerId;

public abstract class AbstractEquity extends IssuedSecurity implements Equity {

    protected AbstractEquity(final String name, final InstrumentIds identifiers, final IssuerId issuerId) {
        super(name, identifiers, issuerId);
    }
}
