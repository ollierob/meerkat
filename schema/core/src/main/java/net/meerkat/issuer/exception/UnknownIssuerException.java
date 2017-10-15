package net.meerkat.issuer.exception;

import net.meerkat.issuer.IssuerId;

/**
 *
 * @author Ollie
 */
public class UnknownIssuerException extends IssuerException {

    private static final long serialVersionUID = 1L;

    public UnknownIssuerException(final IssuerId issuerId) {
        super("Unknown issuer: " + issuerId);
    }

}
