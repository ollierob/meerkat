package net.meerkat.instrument.equity.exception;

import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.instrument.exception.UnknownInstrumentException;

/**
 *
 * @author Ollie
 */
public class UnknownEquityException extends UnknownInstrumentException {

    private static final long serialVersionUID = 1L;

    public UnknownEquityException(InstrumentId instrumentId) {
        super(instrumentId);
    }

}
