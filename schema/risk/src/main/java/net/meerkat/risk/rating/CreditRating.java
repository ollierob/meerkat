package net.meerkat.risk.rating;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.Set;

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

    @CheckForNull
    String agency();

    default int compareTo(final CreditRating that) {
        return this.bestBand().compareTo(that.bestBand());
    }

    CreditRating UNRATED = new UnratedCreditRating();

}
