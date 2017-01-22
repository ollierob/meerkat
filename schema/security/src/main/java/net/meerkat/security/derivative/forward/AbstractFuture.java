package net.meerkat.security.derivative.forward;

import net.meerkat.identifier.security.SecurityIds;
import net.meerkat.security.NamedSecurity;
import net.meerkat.security.Security;

/**
 *
 * @author Ollie
 */
public abstract class AbstractFuture<S extends Security>
        extends NamedSecurity
        implements Future<S> {

    private static final long serialVersionUID = 1L;

    @Deprecated
    protected AbstractFuture() {
    }

    public AbstractFuture(final String name, final SecurityIds identifiers) {
        super(name, identifiers);
    }

}
