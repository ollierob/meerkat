package net.meerkat.identifier.instrument;

import java.util.Collections;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.identifier.HasIds;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class InstrumentIds extends HasIds<InstrumentId> implements HasInstrumentIds {

    private static final long serialVersionUID = 1L;

    public static InstrumentIds of(final InstrumentId id) {
        return new InstrumentIds(Collections.singleton(id));
    }

    @Deprecated
    InstrumentIds() {
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
