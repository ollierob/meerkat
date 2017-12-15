package net.meerkat.identifier.instrument;

import net.coljate.set.Set;
import net.meerkat.identifier.IdSet;

/**
 * @author Ollie
 */
class InstrumentIdSet
        extends IdSet<InstrumentId>
        implements InstrumentIds {

    InstrumentIdSet(final Set<? extends InstrumentId> ids) {
        super(ids);
    }

}
