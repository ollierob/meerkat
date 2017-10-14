package net.meerkat.risk.issue;

/**
 *
 * @author ollie
 */
public interface Issue extends HasIssueId {

    static Issue of(final IssueId id) {
        return new MinimalIssue(id);
    }

}
