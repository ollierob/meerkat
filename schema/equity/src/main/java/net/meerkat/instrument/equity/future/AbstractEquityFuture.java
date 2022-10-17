package net.meerkat.instrument.equity.future;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.derivative.forward.AbstractFuture;
import net.meerkat.instrument.equity.Equity;
import net.meerkat.issuer.IssuerId;

public abstract class AbstractEquityFuture<E extends Equity>
        extends AbstractFuture<E>
        implements EquityFuture<E> {

    protected AbstractEquityFuture(
            final String name,
            final InstrumentIds identifiers,
            final IssuerId issuerId,
            final InstrumentIds underlyingIds) {
        super(name, identifiers, issuerId, underlyingIds);
    }

}
