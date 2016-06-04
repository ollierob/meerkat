package net.ollie.meerkat.identifier;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.goat.money.currency.HasCurrency;
import net.ollie.meerkat.identifier.currency.CurrencyIso;
import net.ollie.meerkat.identifier.market.HasMarketId;
import net.ollie.meerkat.identifier.market.Mic;
import net.ollie.meerkat.identifier.security.HasSecurityId;
import net.ollie.meerkat.identifier.security.Isin;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class IsinAndMarketId
        implements SecurityInMarketId, HasSecurityId, HasMarketId, HasCurrency, Externalizable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "isin")
    private Isin isin;

    @XmlAttribute(name = "market")
    private Mic market;

    @XmlAttribute(name = "currency")
    private CurrencyIso currency;

    @Deprecated
    IsinAndMarketId() {
    }

    public IsinAndMarketId(final Isin isin, final Mic market, final CurrencyIso currency) {
        this.isin = isin;
        this.market = market;
        this.currency = currency;
    }

    @Override
    public Isin securityId() {
        return isin;
    }

    @Override
    public Mic marketId() {
        return market;
    }

    @Override
    public CurrencyIso currency() {
        return currency;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(isin);
        out.writeObject(market);
        out.writeObject(currency);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        isin = (Isin) in.readObject();
        market = (Mic) in.readObject();
        currency = (CurrencyIso) in.readObject();
    }

}
