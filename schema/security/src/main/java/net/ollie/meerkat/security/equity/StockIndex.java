package net.ollie.meerkat.security.equity;

import javax.xml.bind.annotation.XmlRootElement;

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

    public StockIndex(final String name) {
        super(name);
    }

}
