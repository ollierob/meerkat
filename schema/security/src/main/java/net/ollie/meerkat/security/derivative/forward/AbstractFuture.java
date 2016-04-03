package net.ollie.meerkat.security.derivative.forward;

import java.util.Optional;

import javax.annotation.Nonnull;

import net.ollie.meerkat.identifier.security.FutureTicker;
import net.ollie.meerkat.identifier.security.SecurityIds;
import net.ollie.meerkat.security.NamedSecurity;
import net.ollie.meerkat.security.Security;

/**
 *
 * @author Ollie
 */
public abstract class AbstractFuture<S extends Security>
        extends NamedSecurity
        implements Future<S> {

    @Deprecated
    protected AbstractFuture() {
    }

    public AbstractFuture(final String name, final SecurityIds identifiers) {
        super(name, identifiers);
    }

    @Nonnull
    public Optional<FutureTicker> ticker() {
        return this.securityIds().id(FutureTicker.class);
    }

}
