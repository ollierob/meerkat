package net.meerkat.issue;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import net.meerkat.rating.CreditRating;
import net.meerkat.rating.HasCreditRating;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class RatedIssue extends SimpleIssue implements HasCreditRating {

    @XmlElement(name = "rating")
    private CreditRating rating;

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
