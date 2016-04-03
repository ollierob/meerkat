package net.ollie.meerkat.identifier.security;

import java.util.Optional;

/**
 *
 * @author Ollie
 */
public interface HasStockTicker extends HasSecurityIds {

    default Optional<StockTicker> stockTicker() {
        return this.securityIds().id(StockTicker.class);
    }

}
