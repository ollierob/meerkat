package net.ollie.meerkat.identifier.currency;

import net.ollie.meerkat.numeric.Percentage;

/**
 *
 * @author Ollie
 */
public class JPY extends AbstractCurrencyIso {

    private static final long serialVersionUID = 1L;

    @Override
    public String symbol() {
        return "¥";
    }

    @Override
    public Percentage pip() {
        return Percentage.ONE_PERCENT;
    }

    @Override
    public String name() {
        return "Japanese Yen";
    }

}
