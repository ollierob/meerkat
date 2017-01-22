package net.meerkat.identifier.security;

import java.util.Optional;

import javax.annotation.Nonnull;

import net.ollie.meerkat.identifier.security.HasSecurityIds;

/**
 *
 * @author Ollie
 */
public interface HasIsin extends HasNsin, HasSecurityIds {

    @Nonnull
    default Optional<Isin> isin() {
        return this.securityIds().id(Isin.class);
    }

    @Override
    default Optional<Nsin> nsin() {
        return this.isin().flatMap(Isin::nsin);
    }

}
