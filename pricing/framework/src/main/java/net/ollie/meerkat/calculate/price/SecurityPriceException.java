package net.ollie.meerkat.calculate.price;

/**
 *
 * @author Ollie
 */
public class SecurityPriceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SecurityPriceException(final String message) {
        super(message);
    }

    public SecurityPriceException(final String message, final Exception cause) {
        super(message, cause);
    }

}
