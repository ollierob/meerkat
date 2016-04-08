package net.ollie.meerkat.security;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import net.ollie.meerkat.identifier.security.HasSecurityIds;
import net.ollie.meerkat.utils.HasName;

/**
 *
 * @author Ollie
 */
public interface SecurityDefinition extends Security, HasSecurityIds, HasName {

    default String toShortString() {
        return this.securityIds().toString();
    }

    @CheckForNull
    <R> R handleWith(@Nonnull Handler<R> handler);

    interface Handler<R> {

        default R handle(final SecurityDefinition security) {
            return null;
        }

    }

}
