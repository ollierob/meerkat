package net.meerkat.identifier;

import javax.annotation.Nonnull;

import net.meerkat.identifier.instrument.HasInstrumentIds;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.identifier.market.HasMarketId;

/**
 *
 * @author Ollie
 */
public interface HasInstrumentInMarketId extends HasInstrumentIds, HasMarketId {

    @Nonnull
    InstrumentInMarketId instrumentInMarketId();

    @Override
    default InstrumentIds instrumentIds() {
        return this.instrumentInMarketId().instrumentIds();
    }
    
}
