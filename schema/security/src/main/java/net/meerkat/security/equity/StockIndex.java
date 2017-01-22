package net.meerkat.security.equity;

import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.identifier.security.SecurityIds;
import net.meerkat.security.NamedSecurity;

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
