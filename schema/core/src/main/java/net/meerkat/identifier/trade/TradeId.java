package net.meerkat.identifier.trade;

import net.meerkat.utils.Classes.Castable;

/**
 *
 * @author ollie
 */
public interface TradeId extends HasTradeId, Castable {

    @Override
    @Deprecated
    default TradeId tradeId() {
        return this;
    }

}
