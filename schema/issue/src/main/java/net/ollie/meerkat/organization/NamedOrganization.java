package net.ollie.meerkat.organization;

import javax.xml.bind.annotation.XmlAttribute;

import net.ollie.meerkat.utils.HasName;

/**
 *
 * @author ollie
 */
public class NamedOrganization implements Organization, HasName {

    @XmlAttribute(name = "name")
    private String name;

    @Deprecated
    protected NamedOrganization() {
    }

    public NamedOrganization(final String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

}
