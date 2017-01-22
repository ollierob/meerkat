package net.meerkat.rating;

import java.util.Collections;
import java.util.Set;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface CreditRating {

    @Nonnull
    String description();

    @Nonnull
    Set<CreditRatingBand> bands();

    @Nonnull
    default CreditRatingBand bestBand() {
        return Collections.min(this.bands());
    }

    default boolean isInvestmentGrade() {
        return this.bestBand().isInvestmentGrade();
    }

    default boolean isDefault() {
        return this.bestBand().isDefault();
    }

    @Nonnull
    String agency();

    default int compareTo(final CreditRating that) {
        return this.bestBand().compareTo(that.bestBand());
    }

}
