package net.meerkat.pricing.equity.option;

import net.meerkat.pricing.InstrumentPriceException;

/**
 *
 * @author Ollie
 */
public class OptionPriceException extends InstrumentPriceException {

    private static final long serialVersionUID = 1L;

    public OptionPriceException(final String message) {
        super(message);
    }

    public OptionPriceException(final String message, final Exception cause) {
        super(message, cause);
    }

}
