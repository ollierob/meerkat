package net.meerkat.instrument;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.Explainable;
import net.meerkat.identifier.instrument.HasInstrumentIds;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.utils.HasName;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class NamedInstrument
        implements Instrument, HasInstrumentIds, HasName, Externalizable, Explainable {

    private static final long serialVersionUID = 1L;

    @XmlElement(name = "ids", required = true)
    private InstrumentIds identifiers;

    @XmlAttribute(name = "name", required = true)
    private String name;

    @Deprecated
    protected NamedInstrument() {
    }

    public NamedInstrument(final String name, final InstrumentIds identifiers) {
        this.name = name;
        this.identifiers = identifiers;
    }

    @Override
    public InstrumentIds instrumentIds() {
        return identifiers;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + this.explain();
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public ExplanationBuilder explain() {
        return new ExplanationBuilder()
                .put("name", name)
                .put("id", identifiers)
                .put("type", this.getClass().getSimpleName());
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(identifiers);
        out.writeUTF(name);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        identifiers = (InstrumentIds) in.readObject();
        name = in.readUTF();
    }

}
