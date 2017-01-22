package net.meerkat.security;

/**
 *
 * @author Ollie
 */
public class SecurityException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SecurityException(String message) {
        super(message);
    }

    public SecurityException(String message, Exception cause) {
        super(message, cause);
    }

}
