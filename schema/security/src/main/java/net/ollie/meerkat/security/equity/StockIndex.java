package net.ollie.meerkat.security.equity;

import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.identifier.security.SecurityIds;
import net.ollie.meerkat.security.NamedSecurity;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class StockIndex extends NamedSecurity {

    @Deprecated
    StockIndex() {
    }

    public StockIndex(final String name, final SecurityIds identifiers) {
        super(name, identifiers);
    }

}
