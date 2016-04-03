package net.ollie.meerkat.security;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.utils.HasName;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class NamedSecurity implements Security, HasName {

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
