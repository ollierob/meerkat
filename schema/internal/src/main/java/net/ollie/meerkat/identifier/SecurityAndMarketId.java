package net.ollie.meerkat.identifier;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.identifier.currency.HasCurrencyId;
import net.ollie.meerkat.identifier.market.HasMarketId;
import net.ollie.meerkat.identifier.market.MarketId;
import net.ollie.meerkat.identifier.security.HasSecurityId;
import net.ollie.meerkat.identifier.security.SecurityId;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class SecurityAndMarketId
        implements SecurityInMarketId, HasSecurityId, HasMarketId, HasCurrencyId, Externalizable {

    @XmlElementRef(name = "security")
    private SecurityId security;

    @XmlElementRef(name = "market")
    private MarketId market;

    @XmlAttribute(name = "currency")
    private CurrencyId currency;

    @Deprecated
    SecurityAndMarketId() {
    }

    public SecurityAndMarketId(final SecurityId security, final CurrencyId currency, final MarketId market) {
        this.security = security;
        this.currency = currency;
        this.market = market;
    }

    @Override
    public SecurityId securityId() {
        return security;
    }

    @Override
    public CurrencyId currencyId() {
        return currency;
    }

    @Override
    public MarketId marketId() {
        return market;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(security);
        out.writeObject(market);
        out.writeObject(currency);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        security = (SecurityId) in.readObject();
        market = (MarketId) in.readObject();
        currency = (CurrencyId) in.readObject();
    }

}
