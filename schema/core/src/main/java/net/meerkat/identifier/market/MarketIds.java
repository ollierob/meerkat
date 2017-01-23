package net.meerkat.identifier.market;

import java.util.Collections;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.identifier.HasIds;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class MarketIds extends HasIds<MarketId> implements HasMarketIds {

    public static MarketIds of(final MarketId id) {
        return new MarketIds(Collections.singleton(id));
    }

    @Deprecated
    protected MarketIds() {
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
