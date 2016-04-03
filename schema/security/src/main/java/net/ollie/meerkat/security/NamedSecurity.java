package net.ollie.meerkat.security;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.identifier.security.HasSecurityIds;
import net.ollie.meerkat.identifier.security.SecurityIds;
import net.ollie.meerkat.utils.HasName;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class NamedSecurity implements Security, HasSecurityIds, HasName {

    @XmlElement(name = "ids", required = true)
    private SecurityIds identifiers;

    @XmlAttribute(name = "name", required = true)
    private String name;

    @Deprecated
    protected NamedSecurity() {
    }

    public NamedSecurity(final String name, final SecurityIds identifiers) {
        this.name = name;
        this.identifiers = identifiers;
    }

    @Override
    public SecurityIds securityIds() {
        return identifiers;
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
