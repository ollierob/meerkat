package net.meerkat.identifier.instrument;

import net.coljate.set.Set;
import net.meerkat.identifier.Ids;

/**
 * Instrument identifiers for a single instrument. May contain more than one value.
 *
 * @author Ollie
 */
public interface InstrumentIds extends Ids<InstrumentId>, HasInstrumentIds {

    @Override
    @Deprecated
    default InstrumentIds instrumentIds() {
        return this;
    }

    static InstrumentIds of() {
        return NoInstrumentIds.INSTANCE;
    }

    static InstrumentIds of(final InstrumentId id) {
        return new SingletonInstrumentId(id);
    }

    static InstrumentIds of(final InstrumentId... ids) {
        switch (ids.length) {
            case 0:
                return of();
            case 1:
                return of(ids[0]);
            default:
                return new InstrumentIdSet(Set.of(ids));
        }
    }

}
