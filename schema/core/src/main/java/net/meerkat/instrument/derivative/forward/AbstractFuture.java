package net.meerkat.instrument.derivative.forward;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.Instrument;
import net.meerkat.instrument.IssuedSecurity;
import net.meerkat.issuer.IssuerId;

import javax.annotation.Nonnull;

/**
 * @author Ollie
 */
public abstract class AbstractFuture<U extends Instrument>
        extends IssuedSecurity
        implements Future<U> {

    private final InstrumentIds underlyingIds;

    protected AbstractFuture(
            final String name,
            final InstrumentIds identifiers,
            final IssuerId issuerId,
            final InstrumentIds underlyingIds) {
        super(name, identifiers, issuerId);
        this.underlyingIds = underlyingIds;
    }

    @Nonnull
    @Override
    public InstrumentIds underlyingId() {
        return underlyingIds;
    }
}
