package net.meerkat.identifier.instrument.swap;

import net.meerkat.StringWrapper;
import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.identifier.trade.TradeId;
import net.meerkat.identifier.trade.UniqueTradeIdentifier;

/**
 * Unique swap identifier.
 *
 * @author Ollie
 * @see
 * <a href="http://www2.isda.org/functional-areas/technology-infrastructure/data-and-reporting/identifiers/uti-usi/">UTI,
 * USI</a>
 * @see UniqueTradeIdentifier
 */
public class UniqueSwapIdentifier
        extends StringWrapper
        implements InstrumentId, TradeId {

    private final String namespace;

    public UniqueSwapIdentifier(final String namespace, final String transaction) {
        super(transaction);
        this.namespace = namespace;
    }

    public String namespace() {
        return namespace;
    }

    public String transaction() {
        return this.value();
    }

}
