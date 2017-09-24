package net.meerkat.instrument;

import net.meerkat.instrument.exception.UnknownInstrumentException;
import net.coljate.map.Map;
import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.ollie.goat.data.Provider;

/**
 *
 * @author Ollie
 */
public interface InstrumentProvider<I extends Instrument> extends Provider<InstrumentId, I> {

    @Override
    default I require(final InstrumentId id) throws UnknownInstrumentException {
        return this.require(id, UnknownInstrumentException::new);
    }

    default Map<InstrumentId, I> getAll(final InstrumentIds ids) {
        return Map.mapValues(ids.values(), this::get);
    }

}
