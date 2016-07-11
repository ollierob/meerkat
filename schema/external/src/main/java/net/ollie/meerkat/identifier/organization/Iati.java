package net.ollie.meerkat.identifier.organization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.xml.bind.annotation.XmlAttribute;

import net.ollie.meerkat.Explainable;
import net.ollie.meerkat.organization.OrganizationId;

/**
 *
 * @author Ollie
 * @see <a href="http://iatistandard.org/202/organisation-identifiers/">IATI</a>
 */
public class Iati
        implements OrganizationId, Externalizable, Explainable {

    private static final long serialVersionUID = 1L;

    @XmlAttribute(name = "namespace")
    private String namespace;

    @XmlAttribute(name = "base")
    private String base;

    @Deprecated
    Iati() {
    }

    public Iati(final String namespace, final String base) {
        this.namespace = namespace;
        this.base = base;
    }

    @Override
    public String toString() {
        return namespace + '-' + base;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(namespace);
        out.writeUTF(base);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        namespace = in.readUTF();
        base = in.readUTF();
    }

    @Override
    public ExplanationBuilder explain() {
        return new ExplanationBuilder()
                .put("namespace", namespace)
                .put("base", base);
    }

}
