package net.meerkat.issue;

import net.meerkat.Explainable;
import net.meerkat.issuer.IssuerId;

/**
 *
 * @author ollie
 */
public class MinimalIssue implements Issue, Explainable {

    private final IssueId issueId;
    private final IssuerId issuerId;

    public MinimalIssue(final IssueId issueId, final IssuerId issuer) {
        this.issueId = issueId;
        this.issuerId = issuer;
    }

    @Override
    public IssueId issueId() {
        return issueId;
    }

    @Override
    public IssuerId issuerId() {
        return issuerId;
    }

    @Override
    public ExplanationBuilder explain() {
        return this.explanationBuilder()
                .put("issue", issueId)
                .put("issuer", issuerId);
    }

}
