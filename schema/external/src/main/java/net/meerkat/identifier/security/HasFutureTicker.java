package net.meerkat.identifier.security;

import java.util.Optional;

import javax.annotation.Nonnull;

import net.ollie.meerkat.identifier.security.HasSecurityIds;

/**
 *
 * @author Ollie
 */
public interface HasFutureTicker extends HasSecurityIds {

    @Nonnull
    default Optional<FutureTicker> futureTicker() {
        return this.securityIds().id(FutureTicker.class);
    }

}
