package net.meerkat.instrument.equity.exception;

import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.instrument.exception.InvalidInstrumentException;

/**
 *
 * @author Ollie
 */
public class InvalidEquityException extends InvalidInstrumentException {

    private static final long serialVersionUID = 1L;

    public InvalidEquityException(InstrumentId instrumentId) {
        super(instrumentId);
    }

}
