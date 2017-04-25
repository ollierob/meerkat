package net.meerkat.issue;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.issuer.IssuerId;
import net.meerkat.rating.CreditRating;
import net.meerkat.rating.HasCreditRating;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class RatedIssue extends MinimalIssue implements HasCreditRating {

    @XmlElement(name = "rating")
    private CreditRating rating;

    @Deprecated
    protected RatedIssue() {
    }

    public RatedIssue(final IssuerId issuer, final CreditRating rating) {
        super(issuer);
        this.rating = rating;
    }

    @Override
    public CreditRating creditRating() {
        return rating;
    }

    @Override
    public ExplanationBuilder explain() {
        return super.explain()
                .put("credit rating", rating);
    }

}
