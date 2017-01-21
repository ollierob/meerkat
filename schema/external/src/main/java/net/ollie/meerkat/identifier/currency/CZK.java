package net.ollie.meerkat.identifier.currency;

/**
 *
 * @author ollie
 */
public class CZK extends NationalCurrencyIso {

    private static final long serialVersionUID = 1L;

    CZK() {
    }

    @Override
    public String symbol() {
        return "Kč";
    }

    @Override
    public String name() {
        return "Czech koruna";
    }

}
