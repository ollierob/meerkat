package net.ollie.meerkat.rating;

import java.util.Collections;
import java.util.Set;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ollie
 */
@XmlRootElement
public class CreditRatings implements CreditRating {

    @XmlElementRef(name = "final")
    private CreditRating finalRating;

    @XmlElementRef(name = "other")
    private Set<CreditRating> otherRatings;

    @Override
    public String description() {
        return finalRating.description();
    }

    @Override
    public boolean isInvestmentGrade() {
        return finalRating.isInvestmentGrade();
    }

    @Override
    public Set<CreditRatingBand> bands() {
        return finalRating.bands();
    }

    public Set<CreditRating> otherRatings() {
        return Collections.unmodifiableSet(otherRatings);
    }

    @Override
    public String agency() {
        return finalRating.agency();
    }

    @Override
    public int compareTo(final CreditRating that) {
        return finalRating.compareTo(that);
    }

}
