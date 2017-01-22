package net.meerkat.identifier;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.money.currency.HasCurrency;
import net.meerkat.Explainable;
import net.meerkat.identifier.currency.CurrencyIso;
import net.meerkat.identifier.market.HasMarketId;
import net.meerkat.identifier.market.Mic;
import net.meerkat.identifier.security.HasSecurityId;
import net.meerkat.identifier.security.Isin;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class IsinAndMarketId
        implements SecurityInMarketId, HasSecurityId, HasMarketId, HasCurrency, Explainable, Externalizable {

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
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeObject(isin);
        out.writeObject(market);
        out.writeObject(currency);
    }

    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        isin = (Isin) in.readObject();
        market = (Mic) in.readObject();
        currency = (CurrencyIso) in.readObject();
    }

    @Override
    public ExplanationBuilder explain() {
        return new ExplanationBuilder()
                .put("isin", isin)
                .put("market", market)
                .put("currency", currency);
    }

}