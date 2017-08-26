package net.meerkat.instrument;

import javax.annotation.OverridingMethodsMustInvokeSuper;

import net.meerkat.Explainable;
import net.meerkat.Named;
import net.meerkat.identifier.instrument.InstrumentIds;

/**
 *
 * @author Ollie
 */
public class NamedInstrument
        extends Named
        implements Instrument, Explainable {

    private static final long serialVersionUID = 1L;

    private final InstrumentIds ids;

    public NamedInstrument(final String name, final InstrumentIds ids) {
        super(name);
        this.ids = ids;
    }

    @Override
    public InstrumentIds instrumentIds() {
        return ids;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + this.explain();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public ExplanationBuilder explain() {
        return this.explanationBuilder()
                .put("name", this.name())
                .put("ids", ids);
    }

}
