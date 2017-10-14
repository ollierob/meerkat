package net.meerkat.issue;

/**
 *
 * @author ollie
 */
public interface Issue extends HasIssueId {

    static Issue of(final IssueId id) {
        return new MinimalIssue(id);
    }

}
