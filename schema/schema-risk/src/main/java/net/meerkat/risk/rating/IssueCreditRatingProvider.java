package net.meerkat.risk.rating;

import java.time.LocalDate;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import net.meerkat.risk.issue.HasIssueId;
import net.meerkat.risk.issue.IssueId;

/**
 *
 * @author ollie
 */
public interface IssueCreditRatingProvider extends CreditRatingProvider<IssueId> {

    @CheckForNull
    default CreditRating get(final LocalDate date, final HasIssueId hasIssueId) {
        return this.get(date, hasIssueId.issueId());
    }

    @Nonnull
    default CreditRating require(final LocalDate date, final HasIssueId hasIssueId) throws UnavailableCreditRatingException {
        return this.require(date, hasIssueId.issueId());
    }

}
