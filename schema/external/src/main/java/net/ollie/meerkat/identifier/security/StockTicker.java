package net.ollie.meerkat.identifier.security;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.StringWrapper;
import net.ollie.meerkat.identifier.SecurityInMarketId;
import net.ollie.meerkat.identifier.market.HasMarketId;
import net.ollie.meerkat.identifier.market.Mic;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class StockTicker extends StringWrapper implements SecurityInMarketId, SecurityId, HasMarketId {

    @XmlAttribute(name = "market", required = true)
    private Mic mic;

    @Deprecated
    StockTicker() {
    }

    public StockTicker(final Mic mic, final String ticker) {
        super(ticker);
        this.mic = mic;
    }

    @Override
    public Mic marketId() {
        return mic;
    }

    @Override
    public String value() {
        return super.value();
    }

    @Override
    public String toString() {
        return mic + ":" + this.value();
    }

}
