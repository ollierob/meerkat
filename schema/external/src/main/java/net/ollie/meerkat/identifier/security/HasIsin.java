package net.ollie.meerkat.identifier.security;

import java.util.Optional;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface HasIsin extends HasSecurityIds {

    @Nonnull
    default Optional<Isin> isin() {
        return this.securityIds().id(Isin.class);
    }

}
