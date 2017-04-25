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
 * A particular trade for a particular instrument.
 *
 * @author ollie
 */
public class InstrumentAndTradeId
        implements HasInstrumentId, HasTradeId, Externalizable {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "instrumnt")
    private InstrumentId instrument;

    @XmlElementRef(name = "trade")
    private TradeId tradeId;

    @Override
    public InstrumentId instrumentId() {
        return instrument;
    }

    @Override
    public TradeId tradeId() {
        return tradeId;
    }

    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeObject(instrument);
        out.writeObject(tradeId);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        instrument = (InstrumentId) in.readObject();
        tradeId = (TradeId) in.readObject();
    }

}
