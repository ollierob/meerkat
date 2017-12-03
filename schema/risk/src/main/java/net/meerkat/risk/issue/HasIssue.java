package net.meerkat.risk.issue;

/**
 *
 * @author ollie
 */
public interface HasIssue extends HasIssueId {

    Issue issue();

    @Override
    default IssueId issueId() {
        return this.issue().issueId();
    }

}
