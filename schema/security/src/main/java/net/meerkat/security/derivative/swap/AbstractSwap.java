package net.meerkat.security.derivative.swap;

import net.ollie.meerkat.identifier.security.SecurityIds;
import net.meerkat.security.NamedSecurity;

/**
 *
 * @author Ollie
 */
public abstract class AbstractSwap extends NamedSecurity implements Swap {

    @Deprecated
    protected AbstractSwap() {
    }

    protected AbstractSwap(final String name, final SecurityIds identifiers) {
        super(name, identifiers);
    }

}
