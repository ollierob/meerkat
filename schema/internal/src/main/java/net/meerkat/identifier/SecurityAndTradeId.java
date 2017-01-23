package net.meerkat.identifier;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.identifier.instrument.HasInstrumentId;
import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.identifier.trade.HasTradeId;
import net.meerkat.identifier.trade.TradeId;

/**
 *
 * @author ollie
 */
public class SecurityAndTradeId
        implements HasInstrumentId, HasTradeId, Externalizable {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "security")
    private InstrumentId securityId;

    @XmlElementRef(name = "trade")
    private TradeId tradeId;

    @Override
    public InstrumentId instrumentId() {
        return securityId.instrumentId();
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
        securityId = (InstrumentId) in.readObject();
        tradeId = (TradeId) in.readObject();
    }

}
