package net.meerkat.identifier.currency;

/**
 *
 * @author Ollie
 */
public class CAD extends NationalCurrencyIso {

    private static final long serialVersionUID = 1L;

    CAD() {
    }

    @Override
    public String symbol() {
        return "$";
    }

    @Override
    public String name() {
        return "Canadian Dollar";
    }

}
