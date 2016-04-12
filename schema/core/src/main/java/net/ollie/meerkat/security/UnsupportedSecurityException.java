package net.ollie.meerkat.security;

/**
 *
 * @author Ollie
 */
public class UnsupportedSecurityException extends SecurityException {

    private static final long serialVersionUID = 1L;

    public UnsupportedSecurityException(String message) {
        super(message);
    }

}
