package net.meerkat.issue;

import java.util.Optional;

import javax.annotation.Nonnull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.identifier.instrument.InstrumentIds;
import net.meerkat.issuer.IssuerId;
import net.meerkat.rating.CreditRating;
import net.meerkat.rating.HasCreditRating;
import net.meerkat.identifier.instrument.InstrumentId;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class Issue implements HasCreditRating {

    @XmlElementRef(name = "issuer")
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

    public IssuerId issuerId() {
        return issuerId;
    }

}
