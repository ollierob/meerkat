package net.meerkat.identifier.market;

import net.coljate.set.Set;
import net.meerkat.identifier.HasIds;

/**
 *
 * @author ollie
 */
public class MarketIds extends HasIds<MarketId> implements HasMarketIds {

    public static MarketIds of(final MarketId id) {
        return new MarketIds(Set.of(id));
    }

    public MarketIds(final Set<MarketId> ids) {
        super(ids);
    }

    @Override
    @Deprecated
    public MarketIds marketIds() {
        return this;
    }

}
