package net.meerkat.identifier.security;

import java.util.Optional;


/**
 *
 * @author Ollie
 */
public interface HasOptionSymbol extends HasSecurityIds {
    
    default Optional<OptionSymbol> optionSymbol() {
        return this.securityIds().id(OptionSymbol.class);
    }
    
}
