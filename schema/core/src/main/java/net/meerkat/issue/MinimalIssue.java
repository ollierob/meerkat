package net.meerkat.issue;

import net.meerkat.Explainable;

/**
 *
 * @author ollie
 */
public class MinimalIssue implements Issue, Explainable {

    private final IssueId issueId;

    public MinimalIssue(final IssueId issueId) {
        this.issueId = issueId;
    }

    @Override
    public IssueId issueId() {
        return issueId;
    }

    @Override
    public ExplanationBuilder explain() {
        return this.explanationBuilder()
                .put("issue", issueId);
    }

}
