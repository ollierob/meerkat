package net.ollie.meerkat.issue.rating;

import java.util.Set;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ollie
 */
@XmlRootElement
public class LetterOfCredit implements CreditRating {

    @XmlElementRef(name = "underlying")
    private CreditRating underlying;

    @Override
    public boolean isInvestmentGrade() {
        return underlying.isInvestmentGrade();
    }

    @Override
    public String description() {
        return underlying.description();
    }

    @Override
    public Set<CreditRatingBand> bands() {
        return underlying.bands();
    }

    @Override
    public String agency() {
        return underlying.agency();
    }

}
