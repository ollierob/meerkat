package net.meerkat.risk.rating;

import java.util.Collections;
import java.util.Set;

/**
 *
 * @author Ollie
 */
public class CreditRatingsSet implements CreditRating {

    private final CreditRating finalRating;
    private final Set<CreditRating> otherRatings;

    public CreditRatingsSet(final CreditRating finalRating, final Set<CreditRating> otherRatings) {
        this.finalRating = finalRating;
        this.otherRatings = otherRatings;
    }

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
