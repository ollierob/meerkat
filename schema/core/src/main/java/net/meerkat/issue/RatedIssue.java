package net.meerkat.issue;

import net.meerkat.issuer.IssuerId;
import net.meerkat.rating.CreditRating;
import net.meerkat.rating.HasCreditRating;

/**
 *
 * @author ollie
 */
public class RatedIssue extends MinimalIssue implements HasCreditRating {

    private final CreditRating rating;

    public RatedIssue(final IssueId issueId, final IssuerId issuerId, final CreditRating rating) {
        super(issueId, issuerId);
        this.rating = rating;
    }

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
