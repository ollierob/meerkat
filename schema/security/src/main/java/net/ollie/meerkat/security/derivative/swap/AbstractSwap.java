package net.ollie.meerkat.security.derivative.swap;

import net.ollie.meerkat.security.NamedSecurity;

/**
 *
 * @author Ollie
 */
public abstract class AbstractSwap extends NamedSecurity implements Swap {

    @Deprecated
    protected AbstractSwap() {
    }

    protected AbstractSwap(final String name) {
        super(name);
    }

}
