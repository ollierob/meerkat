package net.ollie.meerkat.identifier.security;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class StockTicker implements SecurityId {

    @XmlValue
    private String value;

    @Deprecated
    StockTicker() {
    }

    public StockTicker(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
