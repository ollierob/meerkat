package net.ollie.meerkat.identifier.trade;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.StringWrapper;

/**
 * Unique swap identifier.
 *
 * @author Ollie
 * @see
 * <a href="http://www2.isda.org/functional-areas/technology-infrastructure/data-and-reporting/identifiers/uti-usi/">UTI,
 * USI</a>
 */
@XmlRootElement
public class UniqueSwapIdentifier extends StringWrapper implements TradeId {

    @XmlAttribute(name = "namespace")
    private String namespace;

    @Deprecated
    UniqueSwapIdentifier() {
    }

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
