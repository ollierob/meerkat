package net.meerkat.issue;

import net.meerkat.Explainable;
import net.meerkat.issuer.IssuerId;

/**
 *
 * @author ollie
 */
public class MinimalIssue implements Issue, Explainable {

    private final IssuerId issuerId;

    public MinimalIssue(final IssuerId issuer) {
        this.issuerId = issuer;
    }

    @Override
    public IssuerId issuerId() {
        return issuerId;
    }

    @Override
    public ExplanationBuilder explain() {
        return this.explanationBuilder()
                .put("issuer", issuerId);
    }

}
