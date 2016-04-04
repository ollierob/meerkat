package net.ollie.meerkat.identifier.security;

import java.util.Optional;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface HasNsin extends HasSecurityIds {

    @Nonnull
    default Optional<Nsin> nsin() {
        return this.securityIds().id(Nsin.class);
    }

    @Nonnull
    default <N extends Nsin> Optional<N> nsin(final Class<N> clazz) {
        return this.nsin().flatMap(nsin -> nsin.cast(clazz));
    }

}
