package net.meerkat.identifier.currency;

/**
 *
 * @author ollie
 */
public class VND extends NationalCurrencyIso {

    private static final long serialVersionUID = 1L;

    VND() {
    }

    @Override
    public String symbol() {
        return "₫";
    }

    @Override
    public String name() {
        return "Vietnamese đồng";
    }

}
