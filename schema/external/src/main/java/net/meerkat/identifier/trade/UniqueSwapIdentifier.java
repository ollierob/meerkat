package net.meerkat.identifier.trade;

import net.meerkat.StringWrapper;

/**
 * Unique swap identifier.
 *
 * @author Ollie
 * @see
 * <a href="http://www2.isda.org/functional-areas/technology-infrastructure/data-and-reporting/identifiers/uti-usi/">UTI,
 * USI</a>
 */
public class UniqueSwapIdentifier
        extends StringWrapper
        implements TradeId {

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
