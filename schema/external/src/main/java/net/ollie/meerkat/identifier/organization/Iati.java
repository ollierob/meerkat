package net.ollie.meerkat.identifier.organization;

import javax.xml.bind.annotation.XmlAttribute;

import net.ollie.meerkat.organization.OrganizationId;

/**
 *
 * @author Ollie
 */
public class Iati implements OrganizationId {

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

}
