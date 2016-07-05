package net.ollie.meerkat.identifier.security;

import java.util.function.Consumer;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface HasSecurityIds {

    @Nonnull
    SecurityIds securityIds();

    @Nonnull
    default void securityIds(final Consumer<SecurityId> consumer) {
        this.securityIds().accept(consumer);
    }

}
