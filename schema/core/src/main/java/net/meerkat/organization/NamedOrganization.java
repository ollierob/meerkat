package net.meerkat.organization;

import net.meerkat.Named;

/**
 *
 * @author ollie
 */
public class NamedOrganization
        extends Named
        implements Organization {

    private final OrganizationId id;

    public NamedOrganization(final String name, final OrganizationId id) {
        super(name);
        this.id = id;
    }

    @Override
    public OrganizationId organizationId() {
        return id;
    }

}
