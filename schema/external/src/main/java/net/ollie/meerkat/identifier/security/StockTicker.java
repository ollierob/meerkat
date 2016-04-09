package net.ollie.meerkat.identifier.security;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.annotation.Nonnull;
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
public class StockTicker
        extends StringWrapper
        implements SecurityInMarketId, SecurityId, HasMarketId {

    private static final long serialVersionUID = 1L;

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

    @Nonnull
    public String ticker() {
        return super.value();
    }

    @Override
    public String toString() {
        return mic + ":" + this.value();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeObject(mic);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        mic = (Mic) in.readObject();
    }

}
