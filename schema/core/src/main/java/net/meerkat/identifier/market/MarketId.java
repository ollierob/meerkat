package net.meerkat.identifier.market;

import net.meerkat.objects.Classes.Castable;

/**
 *
 * @author ollie
 */
public interface MarketId extends HasMarketId, Castable {

    @Override
    default MarketId marketId() {
        return this;
    }

}
