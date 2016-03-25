package net.ollie.meerkat.identifier.currency;

/**
 *
 * @author Ollie
 */
public class ZAR extends AbstractCurrencyIso {

    private static final long serialVersionUID = 1L;

    @Override
    public String symbol() {
        return "R";
    }

    @Override
    public String name() {
        return "South African Rand";
    }

}
