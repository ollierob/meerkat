package net.meerkat;

import java.io.Externalizable;

import net.meerkat.utils.HasName;

/**
 *
 * @author ollie
 */
public abstract class Named
        extends StringWrapper
        implements HasName, Externalizable {

    private static final long serialVersionUID = 1L;

    @Deprecated
    protected Named() {
    }

    protected Named(final String value) {
        super(value);
    }

    @Override
    public String name() {
        return this.value();
    }

}
