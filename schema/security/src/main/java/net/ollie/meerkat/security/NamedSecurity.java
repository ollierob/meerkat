package net.ollie.meerkat.security;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class NamedSecurity implements Security {

    @XmlAttribute(name = "name")
    private String name;

    @Deprecated
    protected NamedSecurity() {
    }

    public NamedSecurity(final String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": " + name;
    }

}
