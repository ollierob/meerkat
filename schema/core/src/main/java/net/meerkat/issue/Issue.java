package net.meerkat.issue;

import net.meerkat.issuer.HasIssuerId;
import net.meerkat.issuer.IssuerId;

/**
 *
 * @author ollie
 */
public interface Issue extends HasIssueId, HasIssuerId {

    static Issue of(final IssueId issueId, final IssuerId issuerId) {
        return new MinimalIssue(issueId, issuerId);
    }

}
