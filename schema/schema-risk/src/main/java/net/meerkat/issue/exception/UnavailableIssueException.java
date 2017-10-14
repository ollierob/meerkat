package net.meerkat.issue.exception;

import net.ollie.goat.collection.Arrays;

/**
 *
 * @author Ollie
 */
public class UnavailableIssueException extends IssueException {

    private static final long serialVersionUID = 1L;

    public UnavailableIssueException(final Object... keys) {
        super("Unavailable issue for " + Arrays.toString(keys));
    }

}
