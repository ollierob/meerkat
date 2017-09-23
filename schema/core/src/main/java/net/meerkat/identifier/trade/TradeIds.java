package net.meerkat.identifier.trade;

import net.coljate.set.Set;
import net.meerkat.identifier.IdSet;

/**
 *
 * @author ollie
 */
public class TradeIds extends IdSet<TradeId> implements HasTradeIds {

    private static final long serialVersionUID = 1L;

    public TradeIds(final Set<TradeId> ids) {
        super(ids);
    }

    @Override
    @Deprecated
    public TradeIds tradeIds() {
        return this;
    }

}
