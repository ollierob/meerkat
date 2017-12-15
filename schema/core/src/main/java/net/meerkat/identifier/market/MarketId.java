package net.meerkat.identifier.market;

import net.meerkat.objects.Castable;

/**
 *
 * @author ollie
 */
public interface MarketId extends HasMarketId, Castable<MarketId> {

    @Override
    default MarketId marketId() {
        return this;
    }

}
