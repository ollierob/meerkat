package net.meerkat.identifier;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.identifier.market.HasMarketId;
import net.meerkat.identifier.market.MarketId;
import net.meerkat.identifier.security.HasSecurityId;
import net.meerkat.identifier.security.SecurityId;
import net.meerkat.money.currency.Currency;
import net.meerkat.money.currency.HasCurrency;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class SecurityAndMarketId
        implements SecurityInMarketId, HasSecurityId, HasMarketId, HasCurrency, Externalizable {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "security")
    private SecurityId security;

    @XmlElementRef(name = "market")
    private MarketId market;

    @XmlAttribute(name = "currency")
    private Currency currency;

    @Deprecated
    SecurityAndMarketId() {
    }

    public SecurityAndMarketId(final SecurityId security, final Currency currency, final MarketId market) {
        this.security = security;
        this.currency = currency;
        this.market = market;
    }

    @Override
    public SecurityId securityId() {
        return security;
    }

    @Override
    public Currency currency() {
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
        currency = (Currency) in.readObject();
    }

}
