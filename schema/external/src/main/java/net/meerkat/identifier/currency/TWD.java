package net.meerkat.identifier.currency;

/**
 *
 * @author ollie
 */
public class TWD extends NationalCurrencyIso {

    private static final long serialVersionUID = 1L;

    TWD() {
    }

    @Override
    public String symbol() {
        return "NT$";
    }

    @Override
    public String name() {
        return "New Taiwan dollar";
    }

}
