package net.ollie.meerkat.identifier;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.identifier.currency.CurrencyIso;
import net.ollie.meerkat.identifier.currency.HasCurrencyId;
import net.ollie.meerkat.identifier.market.HasMarketId;
import net.ollie.meerkat.identifier.market.Mic;
import net.ollie.meerkat.identifier.security.Isin;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class IsinInMarketId implements SecurityInMarketId, HasMarketId, HasCurrencyId {

    @XmlElement(name = "isin")
    private Isin isin;

    @XmlAttribute(name = "market")
    private Mic market;

    @XmlAttribute(name = "currency")
    private CurrencyIso currency;

    @Deprecated
    IsinInMarketId() {
    }

    public IsinInMarketId(final Isin isin, final Mic market, final CurrencyIso currency) {
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

}
