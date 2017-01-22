package net.meerkat.identifier.trade;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.StringWrapper;
import net.ollie.meerkat.identifier.trade.TradeId;

/**
 * Unique swap identifier.
 *
 * @author Ollie
 * @see
 * <a href="http://www2.isda.org/functional-areas/technology-infrastructure/data-and-reporting/identifiers/uti-usi/">UTI,
 * USI</a>
 */
@XmlRootElement
public class UniqueSwapIdentifier
        extends StringWrapper
        implements TradeId {

    private static final long serialVersionUID = 1L;

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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeUTF(namespace);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        namespace = in.readUTF();
    }

}
