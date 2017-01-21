package net.ollie.meerkat.identifier.currency;

/**
 *
 * @author Ollie
 */
public class NZD extends NationalCurrencyIso {

    private static final long serialVersionUID = 1L;

    NZD() {
    }

    @Override
    public String symbol() {
        return "$";
    }

    @Override
    public String name() {
        return "New Zealand dollar";
    }

}
