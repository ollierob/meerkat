package net.meerkat.identifier.security;

import java.util.Optional;

import net.ollie.meerkat.identifier.security.HasSecurityIds;

/**
 *
 * @author Ollie
 */
public interface HasOptionSymbol extends HasSecurityIds {
    
    default Optional<OptionSymbol> optionSymbol() {
        return this.securityIds().id(OptionSymbol.class);
    }
    
}
