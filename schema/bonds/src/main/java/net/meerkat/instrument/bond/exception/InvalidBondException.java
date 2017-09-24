package net.meerkat.instrument.bond.exception;

import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.instrument.exception.InvalidInstrumentException;

/**
 *
 * @author ollie
 */
public class InvalidBondException extends InvalidInstrumentException {

    private static final long serialVersionUID = 1L;

    public InvalidBondException(final InstrumentId instrumentId) {
        super("Invalid bond: " + instrumentId);
    }

}
