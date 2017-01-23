package net.meerkat.issuer;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlElementRef;

import net.meerkat.organization.Organization;
import net.meerkat.rating.CreditRating;

/**
 *
 * @author ollie
 * @see CreditRating
 */
public class Issuer implements HasIssuerId, Externalizable {

    private static final long serialVersionUID = 1L;

    @XmlElementRef(name = "id")
    private IssuerId id;

    @XmlElementRef(name = "organization", required = false)
    private Organization organization;

    @Nonnull
    public Organization organization() {
        return organization;
    }

    @Override
    public IssuerId issuerId() {
        return id;
    }

    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeObject(id);
        out.writeObject(organization);
    }

    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        this.id = (IssuerId) in.readObject();
        this.organization = (Organization) in.readObject();
    }

}
