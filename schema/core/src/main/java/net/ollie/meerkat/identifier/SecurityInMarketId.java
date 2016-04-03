package net.ollie.meerkat.identifier;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ollie
 */
@XmlRootElement
public interface SecurityInMarketId extends HasSecurityInMarketId {

    @Override
    @Deprecated
    default SecurityInMarketId securityInMarketId() {
        return this;
    }

}
