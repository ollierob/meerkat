package net.ollie.meerkat.security.derivative.forward;

import net.ollie.meerkat.identifier.security.HasFutureTicker;
import net.ollie.meerkat.identifier.security.SecurityIds;
import net.ollie.meerkat.security.NamedSecurity;
import net.ollie.meerkat.security.Security;

/**
 *
 * @author Ollie
 */
public abstract class AbstractFuture<S extends Security>
        extends NamedSecurity
        implements Future<S>, HasFutureTicker {

    @Deprecated
    protected AbstractFuture() {
    }

    public AbstractFuture(final String name, final SecurityIds identifiers) {
        super(name, identifiers);
    }

}
