package net.ollie.meerkat.organization;

/**
 *
 * @author ollie
 */
public interface OrganizationId extends HasOrganizationId {

    @Override
    @Deprecated
    default OrganizationId organizationId() {
        return this;
    }

}
