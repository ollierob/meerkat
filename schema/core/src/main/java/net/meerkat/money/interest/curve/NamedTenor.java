package net.meerkat.money.interest.curve;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.Period;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.utils.HasName;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class NamedTenor extends Tenor implements HasName {

    private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "name", required = true)
    private String name;

    @Deprecated
    NamedTenor() {
    }

    protected NamedTenor(final String name, final Period period) {
        super(period);
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        super.readExternal(in);
        name = in.readUTF();
    }

    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        super.writeExternal(out);
        out.writeUTF(name);
    }

    @Override
    public ExplanationBuilder explain() {
        return super.explain()
                .put("name", name);
    }

}
