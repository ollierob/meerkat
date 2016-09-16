package net.ollie.meerkat.security;

import java.util.Collections;
import java.util.Map;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import net.ollie.meerkat.Explainable;
import net.ollie.meerkat.identifier.security.HasSecurityIds;
import net.ollie.meerkat.utils.HasName;

/**
 *
 * @author Ollie
 */
public interface SecurityDefinition extends Security, HasSecurityIds, HasName, Explainable {

    default String toShortString() {
        return this.securityIds().toString();
    }

    @Override
    default Map<String, Object> explain() {
        return Collections.emptyMap();
    }

    @CheckForNull
    <R> R handleWith(@Nonnull SecurityDefinition.Handler<R> handler);

    interface Handler<R> {

        default R handle(final SecurityDefinition security) {
            return security.handleWith(this);
        }

    }

}
