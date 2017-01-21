package net.ollie.meerkat.identifier.currency;

/**
 *
 * @author ollie
 */
public class THB extends AbstractCurrencyIso {

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
