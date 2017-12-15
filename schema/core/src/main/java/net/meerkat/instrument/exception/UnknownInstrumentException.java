package net.meerkat.instrument.exception;

/**
 *
 * @author Ollie
 */
public class UnknownInstrumentException extends InstrumentException {

    private static final long serialVersionUID = 1L;

    public UnknownInstrumentException(final Object instrumentId) {
        super("Unknown instrument: " + instrumentId);
    }

}
