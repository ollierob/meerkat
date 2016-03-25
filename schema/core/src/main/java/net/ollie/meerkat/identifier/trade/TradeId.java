package net.ollie.meerkat.identifier.trade;

/**
 *
 * @author ollie
 */
public interface TradeId extends HasTradeId {

    @Override
    default TradeId tradeId() {
        return this;
    }

}
