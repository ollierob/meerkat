package net.meerkat.instrument;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.Explainable;
import net.meerkat.Named;
import net.meerkat.identifier.instrument.HasInstrumentIds;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.utils.HasName;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class NamedInstrument
        extends Named
        implements Instrument, HasInstrumentIds, HasName, Externalizable, Explainable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "ids", required = true)
    private InstrumentIds identifiers;

    @Deprecated
    protected NamedInstrument() {
    }

    public NamedInstrument(final String name, final InstrumentIds identifiers) {
        super(name);
        this.identifiers = identifiers;
    }

    @Override
    public InstrumentIds instrumentIds() {
        return identifiers;
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
                .put("id", identifiers)
                .put("type", this.getClass().getSimpleName());
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void writeExternal(final ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeObject(identifiers);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        identifiers = (InstrumentIds) in.readObject();
    }

}
