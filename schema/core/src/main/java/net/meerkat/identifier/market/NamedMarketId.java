package net.meerkat.identifier.market;

import net.meerkat.objects.HasName;

/**
 * @author ollie
 */
public record NamedMarketId(String name) implements MarketId, HasName {

}
