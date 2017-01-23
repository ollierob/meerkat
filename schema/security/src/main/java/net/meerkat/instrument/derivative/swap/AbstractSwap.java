package net.meerkat.instrument.derivative.swap;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.IssuedSecurity;
import net.meerkat.issuer.IssuerId;

/**
 *
 * @author Ollie
 */
public abstract class AbstractSwap
        extends IssuedSecurity
        implements Swap {

    private static final long serialVersionUID = 1L;

    @Deprecated
    protected AbstractSwap() {
    }

    protected AbstractSwap(final String name, final InstrumentIds identifiers, final IssuerId issuer) {
        super(name, identifiers, issuer);
    }

}
