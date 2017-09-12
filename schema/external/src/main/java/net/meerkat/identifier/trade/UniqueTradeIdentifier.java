package net.meerkat.identifier.trade;


import net.meerkat.StringWrapper;

/**
 *
 * @author ollie
 */
public class UniqueTradeIdentifier
        extends StringWrapper
        implements TradeId {

    private final String namespace;

    public UniqueTradeIdentifier(final String namespace, final String transaction) {
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
