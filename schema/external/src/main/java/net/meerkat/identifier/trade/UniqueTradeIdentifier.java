package net.meerkat.identifier.trade;

import net.meerkat.identifier.instrument.swap.UniqueSwapIdentifier;

/**
 * @author ollie
 * @see UniqueSwapIdentifier
 */
public record UniqueTradeIdentifier(String namespace, String transaction) implements TradeId {

}
