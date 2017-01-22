package net.meerkat.identifier.currency;

/**
 *
 * @author ollie
 */
public class THB extends NationalCurrencyIso {

    private static final long serialVersionUID = 1L;

    THB() {
    }

    @Override
    public String symbol() {
        return "฿";
    }

    @Override
    public String name() {
        return "Thai Baht";
    }

}
