package net.meerkat.identifier.instrument;

import net.coljate.set.Set;
import net.meerkat.identifier.HasIds;

/**
 *
 * @author Ollie
 */
public class InstrumentIds extends HasIds<InstrumentId> implements HasInstrumentIds {

    public static InstrumentIds of(final InstrumentId id) {
        return new InstrumentIds(Set.of(id));
    }

    public InstrumentIds(final Set<InstrumentId> ids) {
        super(ids);
    }

    @Override
    @Deprecated
    public InstrumentIds instrumentIds() {
        return this;
    }

}
