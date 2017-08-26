package net.meerkat.identifier.instrument;

import java.util.Collections;
import java.util.Set;

import net.meerkat.identifier.HasIds;

/**
 *
 * @author Ollie
 */
public class InstrumentIds extends HasIds<InstrumentId> implements HasInstrumentIds {

    private static final long serialVersionUID = 1L;

    public static InstrumentIds of(final InstrumentId id) {
        return new InstrumentIds(Collections.singleton(id));
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
