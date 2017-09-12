package net.meerkat;

import net.meerkat.utils.HasName;

/**
 *
 * @author ollie
 */
public abstract class Named
        extends StringWrapper
        implements HasName {

    private static final long serialVersionUID = 1L;

    protected Named(final String value) {
        super(value);
    }

    @Override
    public String name() {
        return this.value();
    }

}
