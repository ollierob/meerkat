package net.meerkat.risk.issuer.exception;

/**
 *
 * @author Ollie
 */
public class UnavailableIssuerSnapshotException extends IssuerException {

    private static final long serialVersionUID = 1L;

    public UnavailableIssuerSnapshotException(final Object key) {
        super("Issuer snapshot not available for " + key);
    }

}
