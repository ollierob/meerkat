package net.ollie.meerkat.identifier.security;

import net.ollie.meerkat.utils.Classes.Castable;

/**
 *
 * @author Ollie
 */
public interface SecurityId extends HasSecurityId, Castable<SecurityId> {

    @Override
    @Deprecated
    default SecurityId securityId() {
        return this;
    }

}
