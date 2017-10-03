package net.meerkat.rating;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

import net.meerkat.issue.HasIssueId;
import net.meerkat.issue.IssueId;

/**
 *
 * @author ollie
 */
public interface IssueCreditRatingProvider extends CreditRatingProvider<IssueId> {

    @CheckForNull
    default CreditRating get(final HasIssueId hasIssueId) {
        return this.get(hasIssueId.issueId());
    }

    @Nonnull
    default CreditRating require(final HasIssueId hasIssueId) throws UnavailableCreditRatingException {
        return this.require(hasIssueId.issueId());
    }

}
