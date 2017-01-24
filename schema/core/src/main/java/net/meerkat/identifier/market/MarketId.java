package net.meerkat.identifier.market;

import net.meerkat.utils.Classes.Castable;

/**
 *
 * @author ollie
 */
public interface MarketId extends HasMarketId, Castable {

    @Override
    default MarketId marketId() {
        return this;
    }

    default MarketIds toMarketIds() {
        return MarketIds.of(this);
    }

}
