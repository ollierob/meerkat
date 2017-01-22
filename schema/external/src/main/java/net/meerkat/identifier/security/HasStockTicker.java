package net.meerkat.identifier.security;

import java.util.Optional;

import net.ollie.meerkat.identifier.security.HasSecurityIds;

/**
 *
 * @author Ollie
 */
public interface HasStockTicker extends HasSecurityIds {

    default Optional<StockTicker> stockTicker() {
        return this.securityIds().id(StockTicker.class);
    }

}
