package net.meerkat.identifier.instrument;

import net.coljate.set.Set;
import net.meerkat.identifier.Ids;

/**
 *
 * @author Ollie
 */
public interface InstrumentIds extends Ids<InstrumentId>, HasInstrumentIds {

    public static InstrumentIds of(final InstrumentId id) {
        return new InstrumentIdSet(Set.of(id));
    }

    public static InstrumentIds of(final InstrumentId... ids) {
        return new InstrumentIdSet(Set.of(ids));
    }

    @Override
    @Deprecated
    default InstrumentIds instrumentIds() {
        return this;
    }

}
