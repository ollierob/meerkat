package net.meerkat.organization;

import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.Named;

/**
 *
 * @author ollie
 */
public class NamedOrganization
        extends Named
        implements Organization {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "id")
    private OrganizationId id;

    @Deprecated
    protected NamedOrganization() {
    }

    public NamedOrganization(final String name, final OrganizationId id) {
        super(name);
        this.id = id;
    }

    @Override
    public OrganizationId organizationId() {
        return id;
    }

}
