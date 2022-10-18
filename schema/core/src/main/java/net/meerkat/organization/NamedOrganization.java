package net.meerkat.organization;

import net.meerkat.objects.HasName;

/**
 * @author ollie
 */
public record NamedOrganization(OrganizationId organizationId, String name) implements Organization, HasName {

}
