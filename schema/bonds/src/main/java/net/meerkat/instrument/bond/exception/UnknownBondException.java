package net.meerkat.instrument.bond.exception;

import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.instrument.exception.UnknownInstrumentException;

/**
 *
 * @author Ollie
 */
public class UnknownBondException extends UnknownInstrumentException {

    private static final long serialVersionUID = 1L;

    public UnknownBondException(final InstrumentId instrumentId) {
        super(instrumentId);
    }

}
