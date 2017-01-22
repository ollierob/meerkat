package net.meerkat.identifier.market;

/**
 *
 * @author ollie
 */
public interface MarketId extends HasMarketId {

    @Override
    default MarketId marketId() {
        return this;
    }

}
