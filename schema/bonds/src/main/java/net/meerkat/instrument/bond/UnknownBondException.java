package net.meerkat.instrument.bond;

import net.meerkat.identifier.instrument.InstrumentId;
import net.meerkat.instrument.UnknownInstrumentException;

/**
 *
 * @author Ollie
 */
public class UnknownBondException extends UnknownInstrumentException {

    private static final long serialVersionUID = 1L;

    public UnknownBondException(InstrumentId instrumentId) {
        super(instrumentId);
    }

}
