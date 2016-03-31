package net.ollie.meerkat.issue.rating;

import java.util.Collections;
import java.util.Set;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface CreditRating {

    @Nonnull
    Set<CreditRatingBand> bands();

    default CreditRatingBand bestBand() {
        return Collections.min(this.bands());
    }

    default boolean isInvestmentGrade() {
        return this.bestBand().isInvestmentGrade();
    }

    @Nonnull
    String agency();

    default int compareTo(final CreditRating that) {
        return this.bestBand().compareTo(that.bestBand());
    }

}
