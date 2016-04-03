package net.ollie.meerkat.identifier.security;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface HasSecurityId extends HasSecurityIds {

    @Nonnull
    SecurityId securityId();

    @Override
    @Deprecated
    default SecurityIds securityIds() {
        return SecurityIds.singleton(this.securityId());
    }

}
