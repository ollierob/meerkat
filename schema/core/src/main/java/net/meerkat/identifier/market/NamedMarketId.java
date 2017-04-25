package net.meerkat.identifier.market;

import net.meerkat.Named;
import net.meerkat.identifier.market.MarketId;

/**
 *
 * @author ollie
 */
public class NamedMarketId 
        extends Named 
        implements MarketId {

    private static final long serialVersionUID = 1L;

    @Deprecated
    NamedMarketId() {
    }

    public NamedMarketId(final String value) {
        super(value);
    }

}
