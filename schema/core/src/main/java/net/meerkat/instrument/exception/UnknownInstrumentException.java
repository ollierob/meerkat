package net.meerkat.instrument.exception;

import net.meerkat.identifier.instrument.InstrumentId;

/**
 *
 * @author Ollie
 */
public class UnknownInstrumentException extends InstrumentException {

    private static final long serialVersionUID = 1L;

    public UnknownInstrumentException(final InstrumentId instrumentId) {
        super("Unknown instrument: " + instrumentId);
    }

    public UnknownInstrumentException(String message) {
        super(message);
    }

}
