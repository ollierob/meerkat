package net.meerkat.identifier.instrument;

import net.coljate.set.Set;
import net.meerkat.identifier.IdSet;

/**
 *
 * @author Ollie
 */
public class InstrumentIdSet
        extends IdSet<InstrumentId>
        implements InstrumentIds {

    public InstrumentIdSet(final Set<? extends InstrumentId> ids) {
        super(ids);
    }

    @Override
    @Deprecated
    public InstrumentIdSet instrumentIds() {
        return this;
    }

}
