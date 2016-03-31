package net.ollie.meerkat.identifier;

import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.identifier.security.HasSecurityId;
import net.ollie.meerkat.identifier.security.SecurityId;
import net.ollie.meerkat.identifier.trade.HasTradeId;
import net.ollie.meerkat.identifier.trade.TradeId;

/**
 *
 * @author ollie
 */
public class SecurityAndTradeId implements HasSecurityId, HasTradeId {

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

}
