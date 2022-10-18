package net.meerkat.pricing;

import net.meerkat.instrument.exception.InstrumentException;

/**
 *
 * @author Ollie
 */
public class InstrumentPriceException extends InstrumentException {

    public InstrumentPriceException(final String message) {
        super(message);
    }

    public InstrumentPriceException(final Exception cause) {
        super(cause);
    }

    public InstrumentPriceException(final String message, final Exception cause) {
        super(message, cause);
    }

}
