package net.ollie.meerkat.identifier.trade;

import javax.xml.bind.annotation.XmlElementRef;

import net.ollie.meerkat.identifier.security.HasSecurityId;
import net.ollie.meerkat.identifier.security.SecurityId;

/**
 *
 * @author ollie
 */
public class TradeAndSecurityId implements HasSecurityId, HasTradeId {

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
