package net.meerkat.risk.rating;

import net.meerkat.risk.issue.HasIssueId;
import net.meerkat.risk.issue.IssueId;
import net.meerkat.risk.rating.exception.UnavailableCreditRatingException;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface IssueCreditRatingProvider<T> extends CreditRatingProvider<T, IssueId> {

    @CheckForNull
    default CreditRating get(final T temporal, final HasIssueId hasIssueId) {
        return this.get(temporal, hasIssueId.issueId());
    }

    @Nonnull
    default CreditRating require(final T temporal, final HasIssueId hasIssueId) throws UnavailableCreditRatingException {
        return this.require(temporal, hasIssueId.issueId());
    }

}
