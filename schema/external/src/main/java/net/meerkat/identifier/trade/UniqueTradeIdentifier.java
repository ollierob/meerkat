package net.meerkat.identifier.trade;

import javax.xml.bind.annotation.XmlAttribute;

import net.meerkat.StringWrapper;

/**
 *
 * @author ollie
 */
public class UniqueTradeIdentifier
        extends StringWrapper
        implements TradeId {

    private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "namespace", required = true)
    private String namespace;

    @Deprecated
    UniqueTradeIdentifier() {
    }

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
