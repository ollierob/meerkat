package net.ollie.meerkat;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author ollie
 */
public abstract class StringWrapper {

    @XmlValue
    private String value;

    @Deprecated
    protected StringWrapper() {
    }

    protected StringWrapper(@Nonnull final String value) {
        this.value = Objects.requireNonNull(value);
    }

    @Nonnull
    protected String value() {
        return value;
    }

    protected char first() {
        return value.charAt(0);
    }

    protected char last() {
        return value.charAt(value.length() - 1);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(final Object that) {
        return that != null
                && this.getClass() == that.getClass()
                && value.equals(((StringWrapper) that).value);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.value);
        return hash;
    }

}
