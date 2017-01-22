package net.meerkat.identifier;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.identifier.security.HasSecurityId;
import net.ollie.meerkat.identifier.security.SecurityId;
import net.ollie.meerkat.identifier.trade.HasTradeId;
import net.ollie.meerkat.identifier.trade.TradeId;

/**
 *
 * @author ollie
 */
public class SecurityAndTradeId
        implements HasSecurityId, HasTradeId, Externalizable {

    @XmlElementRef(name = "security")
    private SecurityId securityId;

    @XmlElementRef(name = "trade")
    private TradeId tradeId;

    @Override
    public SecurityId securityId() {
        return securityId.securityId();
    }

    @Override
    public TradeId tradeId() {
        return tradeId.tradeId();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(securityId);
        out.writeObject(tradeId);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        securityId = (SecurityId) in.readObject();
        tradeId = (TradeId) in.readObject();
    }

}
