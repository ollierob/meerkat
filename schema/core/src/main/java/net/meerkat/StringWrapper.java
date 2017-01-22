package net.meerkat;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.xml.bind.annotation.XmlValue;

/**
 *
 * @author ollie
 */
public abstract class StringWrapper implements Externalizable {

    private static final long serialVersionUID = 1L;

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

    @Override
    @OverridingMethodsMustInvokeSuper
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(value);
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        value = in.readUTF();
    }

}
