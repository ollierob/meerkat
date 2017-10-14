package net.meerkat.risk.issue;

import net.meerkat.risk.issuer.RatedIssuer;
import net.meerkat.risk.rating.CreditRating;
import net.meerkat.risk.rating.HasCreditRating;

/**
 *
 * @author ollie
 * @see RatedIssuer
 */
public class RatedIssue extends MinimalIssue implements HasCreditRating {

    private final CreditRating rating;

    public RatedIssue(final IssueId issueId, final CreditRating rating) {
        super(issueId);
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
