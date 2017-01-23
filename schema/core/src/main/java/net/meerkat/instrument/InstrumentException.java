package net.meerkat.instrument;

/**
 *
 * @author Ollie
 */
public class InstrumentException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InstrumentException(String message) {
        super(message);
    }

    public InstrumentException(String message, Exception cause) {
        super(message, cause);
    }

}
