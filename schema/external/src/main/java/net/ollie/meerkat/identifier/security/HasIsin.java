package net.ollie.meerkat.identifier.security;

import java.util.Optional;

import javax.annotation.Nonnull;

import net.ollie.meerkat.IdentifiedSecurity;

/**
 *
 * @author Ollie
 */
public interface HasIsin extends IdentifiedSecurity {

    @Nonnull
    default Optional<Isin> isin() {
        return this.securityIds().id(Isin.class);
    }

}
