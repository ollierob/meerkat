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
import net.meerkat.identifier.instrument.InstrumentIds;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class NamedInstrument
        extends Named
        implements Instrument, Externalizable, Explainable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "ids", required = true)
    private InstrumentIds ids;

    @Deprecated
    protected NamedInstrument() {
    }

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

    @Override
    @OverridingMethodsMustInvokeSuper
    public void writeExternal(final ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeObject(ids);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        ids = (InstrumentIds) in.readObject();
    }

}
