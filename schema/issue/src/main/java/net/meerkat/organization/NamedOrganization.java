package net.meerkat.organization;

import net.meerkat.StringWrapper;

/**
 *
 * @author ollie
 */
public class NamedOrganization
        extends StringWrapper
        implements Organization {

    private static final long serialVersionUID = 1L;

    @Deprecated
    protected NamedOrganization() {
    }

    public NamedOrganization(final String name) {
        super(name);
    }

    @Override
    public String name() {
        return this.value();
    }

}
