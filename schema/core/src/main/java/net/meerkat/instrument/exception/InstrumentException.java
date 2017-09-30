package net.meerkat.instrument.exception;

/**
 *
 * @author Ollie
 */
public class InstrumentException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InstrumentException(final String message) {
        super(message);
    }

    public InstrumentException(final Exception cause) {
        super(cause);
    }

    public InstrumentException(final String message, final Exception cause) {
        super(message, cause);
    }

}
