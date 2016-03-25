package net.ollie.meerkat.security;

import net.ollie.meerkat.utils.HasName;

/**
 *
 * @author Ollie
 */
public interface Security extends HasName, HasSecurity {

    @Override
    default Security security() {
        return this;
    }
    
}
