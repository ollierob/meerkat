package net.meerkat.instrument.derivative.forward;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.Instrument;
import net.meerkat.instrument.IssuedSecurity;
import net.meerkat.issuer.IssuerId;

/**
 *
 * @author Ollie
 */
public abstract class AbstractFuture<U extends Instrument>
        extends IssuedSecurity
        implements Future<U> {

    protected AbstractFuture(final String name, final InstrumentIds identifiers, final IssuerId issuerId) {
        super(name, identifiers, issuerId);
    }

}
