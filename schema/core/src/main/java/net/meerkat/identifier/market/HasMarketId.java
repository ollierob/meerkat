package net.meerkat.identifier.market;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface HasMarketId extends HasMarketIds {

    @Nonnull
    MarketId marketId();

    @Override
    default MarketIds marketIds() {
        return MarketIds.of(this.marketId());
    }

}
