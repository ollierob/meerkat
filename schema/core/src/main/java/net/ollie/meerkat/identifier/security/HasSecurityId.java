package net.ollie.meerkat.identifier.security;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface HasSecurityId {

    @Nonnull
    SecurityId securityId();

}
