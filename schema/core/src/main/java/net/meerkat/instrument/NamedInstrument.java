package net.meerkat.instrument;

import net.meerkat.Explainable;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.objects.HasName;

import javax.annotation.OverridingMethodsMustInvokeSuper;

/**
 *
 * @author Ollie
 */
public class NamedInstrument
        extends IdentifiedInstrument
        implements HasName, Explainable {

    private final String name;

    public NamedInstrument(final String name, final InstrumentIds ids) {
        super(ids);
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public ExplanationBuilder explain() {
        return super.explain().put("name", this.name());
    }

}
