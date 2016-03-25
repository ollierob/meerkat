package net.ollie.meerkat.identifier;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.identifier.currency.CurrencyId;
import net.ollie.meerkat.identifier.currency.HasCurrencyId;
import net.ollie.meerkat.identifier.market.HasMarketId;
import net.ollie.meerkat.identifier.market.MarketId;
import net.ollie.meerkat.identifier.security.SecurityId;

/**
 *
 * @author Ollie
 */
public class SecurityAndMarketId implements SecurityInMarketId, HasMarketId, HasCurrencyId {

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

}
