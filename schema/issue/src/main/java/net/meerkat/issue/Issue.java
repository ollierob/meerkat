package net.meerkat.issue;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.issuer.IssuerId;
import net.meerkat.rating.CreditRating;
import net.meerkat.rating.HasCreditRating;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class Issue implements HasCreditRating {

    @XmlElementRef(name = "issueId")
    private IssueId issueId;

    @XmlElementRef(name = "issuerId")
    private IssuerId issuerId;

    @XmlElement(name = "ids")
    private InstrumentIds identifiers;

    @XmlElement(name = "rating")
    private CreditRating rating;

    @Override
    public CreditRating creditRating() {
        return rating;
    }

    @Nonnull
    public <S extends InstrumentId> Optional<S> id(final Class<S> clazz) {
        return identifiers.id(clazz);
    }

    public IssueId issueId() {
        return issueId;
    }

    public IssuerId issuerId() {
        return issuerId;
    }

}
