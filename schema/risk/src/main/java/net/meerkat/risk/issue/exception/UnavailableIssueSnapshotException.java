package net.meerkat.risk.issue.exception;

import net.meerkat.collections.Arrays;

/**
 *
 * @author Ollie
 */
public class UnavailableIssueSnapshotException extends IssueException {

    private static final long serialVersionUID = 1L;

    public UnavailableIssueSnapshotException(final Object... keys) {
        super("Unavailable issue for " + Arrays.toString(keys));
    }

}
