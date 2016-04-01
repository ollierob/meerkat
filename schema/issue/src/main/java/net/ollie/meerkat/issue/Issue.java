package net.ollie.meerkat.issue;

import java.util.Optional;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.ollie.meerkat.identifier.security.SecurityId;
import net.ollie.meerkat.organization.OrganizationId;
import net.ollie.meerkat.rating.CreditRating;
import net.ollie.meerkat.rating.HasCreditRating;
import net.ollie.meerkat.utils.Classes;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class Issue implements HasCreditRating {

    @XmlElementRef(name = "issuer")
    private OrganizationId issuerId;

    @XmlElementRef(name = "id")
    private Set<SecurityId> identifiers;

    @XmlElement(name = "rating")
    private CreditRating rating;

    @Override
    public CreditRating creditRating() {
        return rating;
    }

    public <S extends SecurityId> Optional<S> id(final Class<S> clazz) {
        return identifiers.stream()
                .map(id -> Classes.cast(id, clazz))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findAny();
    }

    public OrganizationId issuerId() {
        return issuerId;
    }

}
