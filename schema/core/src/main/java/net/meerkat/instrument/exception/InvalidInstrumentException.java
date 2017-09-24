package net.meerkat.instrument.exception;

import net.meerkat.identifier.instrument.InstrumentId;

/**
 *
 * @author ollie
 */
public class InvalidInstrumentException extends InstrumentException {

    private static final long serialVersionUID = 1L;

    public InvalidInstrumentException(final InstrumentId instrumentId) {
        this("Invalid instrument: " + instrumentId);
    }

    protected InvalidInstrumentException(String message) {
        super(message);
    }

}
