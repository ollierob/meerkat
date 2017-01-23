package net.meerkat.identifier;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.identifier.instrument.HasInstrumentId;
import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.identifier.market.HasMarketId;
import net.meerkat.identifier.market.MarketId;
import net.meerkat.money.currency.CurrencyId;
import net.meerkat.money.currency.HasCurrency;

/**
 * A particular instrument in a particular market in a particular currency.
 *
 * @author Ollie
 */
@XmlRootElement
public class InstrumentAndMarketId
        implements InstrumentInMarketId, HasInstrumentId, HasMarketId, HasCurrency, Externalizable {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "instrument")
    private InstrumentId instrumntId;

    @XmlElementRef(name = "market")
    private MarketId market;

    @XmlAttribute(name = "currency")
    private CurrencyId currency;

    @Deprecated
    InstrumentAndMarketId() {
    }

    public InstrumentAndMarketId(final InstrumentId security, final CurrencyId currency, final MarketId market) {
        this.instrumntId = security;
        this.currency = currency;
        this.market = market;
    }

    @Override
    public InstrumentId instrumentId() {
        return instrumntId;
    }

    @Override
    public InstrumentIds instrumentIds() {
        return HasInstrumentId.super.instrumentIds();
    }

    @Override
    public CurrencyId currency() {
        return currency;
    }

    @Override
    public MarketId marketId() {
        return market;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(instrumntId);
        out.writeObject(market);
        out.writeObject(currency);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        instrumntId = (InstrumentId) in.readObject();
        market = (MarketId) in.readObject();
        currency = (CurrencyId) in.readObject();
    }

}
