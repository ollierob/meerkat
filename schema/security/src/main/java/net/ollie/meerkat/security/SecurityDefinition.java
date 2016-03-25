package net.ollie.meerkat.security;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;


/**
 *
 * @author Ollie
 */
public interface SecurityDefinition extends Security {

    @CheckForNull
    <R> R handleWith(@Nonnull Handler<R> handler);

    interface Handler<R> {

        default R handle(final Security security) {
            return null;
        }

    }

}
