package net.ollie.meerkat.calculate.price;

import net.meerkat.instrument.InstrumentException;

/**
 *
 * @author Ollie
 */
public class InstrumentPriceException extends InstrumentException {

    private static final long serialVersionUID = 1L;

    public InstrumentPriceException(final String message) {
        super(message);
    }

    public InstrumentPriceException(final String message, final Exception cause) {
        super(message, cause);
    }

}
