package net.meerkat.instrument;

import javax.annotation.Nonnull;

import net.coljate.map.Map;
import net.coljate.set.Set;
import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.instrument.exception.UnknownInstrumentException;
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

    @Nonnull
    default Map<InstrumentId, I> getAll(final Set<? extends InstrumentId> ids) {
        return Map.mapValues(ids, this::get);
    }

    @Nonnull
    default Map<InstrumentId, I> requireAll(final Set<? extends InstrumentId> ids) {
        return Map.mapValues(ids, this::require);
    }

}
