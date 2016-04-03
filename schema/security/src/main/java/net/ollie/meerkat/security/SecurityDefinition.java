package net.ollie.meerkat.security;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import net.ollie.meerkat.IdentifiedSecurity;

/**
 *
 * @author Ollie
 */
public interface SecurityDefinition extends IdentifiedSecurity {

    @CheckForNull
    <R> R handleWith(@Nonnull Handler<R> handler);

    interface Handler<R> {

        default R handle(final Security security) {
            return null;
        }

    }

}
