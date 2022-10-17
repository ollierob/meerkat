package net.meerkat.identifier.instrument.swap;

import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.identifier.trade.UniqueTradeIdentifier;

/**
 * Unique swap identifier.
 *
 * @author Ollie
 * @see <a href="http://www2.isda.org/functional-areas/technology-infrastructure/data-and-reporting/identifiers/uti-usi/">USI</a>
 * @see UniqueTradeIdentifier
 */
public record UniqueSwapIdentifier(String namespace, String transaction) implements InstrumentId {

}
