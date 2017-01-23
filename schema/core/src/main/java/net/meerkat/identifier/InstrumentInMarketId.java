package net.meerkat.identifier;

import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.identifier.market.MarketId;

/**
 *
 * @author ollie
 */
@XmlRootElement
public interface InstrumentInMarketId extends HasInstrumentInMarketId {

    @Override
    InstrumentIds instrumentIds();

    @Override
    MarketId marketId();

    @Override
    @Deprecated
    default InstrumentInMarketId instrumentInMarketId() {
        return this;
    }

}
