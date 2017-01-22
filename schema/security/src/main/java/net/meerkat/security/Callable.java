package net.meerkat.security;

import java.util.Optional;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface Callable<C> {

    @Nonnull
    Optional<? extends C> call();

    default boolean isCallable() {
        return this.call().isPresent();
    }

}
