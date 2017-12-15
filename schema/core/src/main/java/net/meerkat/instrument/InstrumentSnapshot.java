package net.meerkat.instrument;

import net.coljate.map.Map;
import net.coljate.set.Set;
import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.instrument.exception.UnknownInstrumentException;
import net.ollie.goat.data.Provider;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 * @author Ollie
 */
public interface InstrumentSnapshot<I extends Instrument> extends Provider<InstrumentId, I> {

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

    @CheckForNull
    default I get(final InstrumentIds ids) {
        for (final InstrumentId id : ids.values()) {
            final I instrument = this.get(id);
            if (instrument != null) {
                return instrument;
            }
        }
        return null;
    }

    @Nonnull
    default I require(final InstrumentIds ids) throws UnknownInstrumentException {
        final I instrument = this.get(ids);
        if (instrument == null) {
            throw new UnknownInstrumentException(ids);
        }
        return instrument;
    }

}
