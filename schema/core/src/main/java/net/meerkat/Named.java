package net.meerkat;

import net.meerkat.utils.HasName;

/**
 *
 * @author ollie
 */
public abstract class Named
        extends StringWrapper
        implements HasName {

    protected Named(final String value) {
        super(value);
    }

    @Override
    public String name() {
        return this.value();
    }

}
