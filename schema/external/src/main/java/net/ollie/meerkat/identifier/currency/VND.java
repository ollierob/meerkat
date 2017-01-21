package net.ollie.meerkat.identifier.currency;

/**
 *
 * @author ollie
 */
public class VND extends AbstractCurrencyIso {

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
