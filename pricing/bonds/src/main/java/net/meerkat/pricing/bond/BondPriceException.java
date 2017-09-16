package net.meerkat.pricing.bond;

import net.meerkat.pricing.InstrumentPriceException;

/**
 *
 * @author Ollie
 */
public class BondPriceException extends InstrumentPriceException {

    private static final long serialVersionUID = 1L;

    public BondPriceException(final String message) {
        super(message);
    }

}
