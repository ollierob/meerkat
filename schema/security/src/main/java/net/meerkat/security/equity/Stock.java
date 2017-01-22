package net.meerkat.security.equity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.identifier.security.SecurityIds;
import net.meerkat.security.NamedSecurity;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class Stock
        extends NamedSecurity
        implements Equity {

    private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "preferred")
    private boolean preferred;

    @Deprecated
    Stock() {
    }

    public Stock(
            final String name,
            final SecurityIds identifiers,
            final boolean preferred) {
        super(name, identifiers);
        this.preferred = preferred;
    }

    public boolean isPreferred() {
        return preferred;
    }

    @Override
    public <R> R handleWith(final Equity.Handler<R> handler) {
        return handler.handle(this);
    }

}
