package net.meerkat.issuer;

import net.meerkat.organization.HasOrganizationId;
import net.meerkat.organization.OrganizationId;

/**
 *
 * @author ollie
 */
public interface IssuerId extends HasOrganizationId {

    @Override
    OrganizationId organizationId();

}
