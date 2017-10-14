package net.meerkat.risk.issue.exception;

/**
 *
 * @author Ollie
 */
public class IssueException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IssueException(final String message) {
        super(message);
    }

    public IssueException(final String message, final Exception cause) {
        super(message, cause);
    }

}
