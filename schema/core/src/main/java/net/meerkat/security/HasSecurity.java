package net.meerkat.security;

import javax.annotation.Nonnull;

/**
 *
 * @author Ollie
 */
public interface HasSecurity {

    @Nonnull
    Security security();

}
