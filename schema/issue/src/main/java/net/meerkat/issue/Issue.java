package net.meerkat.issue;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.identifier.security.SecurityId;
import net.meerkat.identifier.security.SecurityIds;
import net.meerkat.organization.OrganizationId;
import net.meerkat.rating.CreditRating;
import net.meerkat.rating.HasCreditRating;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class Issue implements HasCreditRating {

    @XmlElementRef(name = "issuer")
    private OrganizationId issuerId;

    @XmlElement(name = "ids")
    private SecurityIds identifiers;

    @XmlElement(name = "rating")
    private CreditRating rating;

    @Override
    public CreditRating creditRating() {
        return rating;
    }

    @Nonnull
    public <S extends SecurityId> Optional<S> id(final Class<S> clazz) {
        return identifiers.id(clazz);
    }

    public OrganizationId issuerId() {
        return issuerId;
    }

}
